package com.secretnotes;
import android.content.*;import android.util.Base64;import java.nio.charset.StandardCharsets;import java.security.*;import javax.crypto.*;import javax.crypto.spec.GCMParameterSpec;import javax.crypto.spec.SecretKeySpec;
public class SecurePrefs {
 private static final String PREF="secure_settings", PIN="pin"; private final SharedPreferences p; private final byte[] key;
 public SecurePrefs(Context c){p=c.getSharedPreferences(PREF,Context.MODE_PRIVATE);key=loadKey();}
 private byte[] loadKey(){try{String s=p.getString("key",null);if(s!=null)return Base64.decode(s,Base64.DEFAULT);byte[] k=new byte[32];new SecureRandom().nextBytes(k);p.edit().putString("key",Base64.encodeToString(k,Base64.NO_WRAP)).apply();return k;}catch(Exception e){throw new IllegalStateException(e);}}
 public boolean hasPin(){return p.contains(PIN);}
 public void setPin(String pin){try{byte[] iv=new byte[12];new SecureRandom().nextBytes(iv);Cipher c=Cipher.getInstance("AES/GCM/NoPadding");c.init(Cipher.ENCRYPT_MODE,new SecretKeySpec(key,"AES"),new GCMParameterSpec(128,iv));byte[] encrypted=c.doFinal(pin.getBytes(StandardCharsets.UTF_8));byte[] out=new byte[iv.length+encrypted.length];System.arraycopy(iv,0,out,0,iv.length);System.arraycopy(encrypted,0,out,iv.length,encrypted.length);p.edit().putString(PIN,Base64.encodeToString(out,Base64.NO_WRAP)).apply();}catch(Exception e){throw new IllegalStateException(e);}}
 public boolean verify(String pin){try{byte[] x=Base64.decode(p.getString(PIN,""),Base64.DEFAULT);Cipher c=Cipher.getInstance("AES/GCM/NoPadding");c.init(Cipher.DECRYPT_MODE,new SecretKeySpec(key,"AES"),new GCMParameterSpec(128,x,0,12));return pin.equals(new String(c.doFinal(x,12,x.length-12),StandardCharsets.UTF_8));}catch(Exception e){return false;}}
}
