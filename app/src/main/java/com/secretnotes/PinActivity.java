package com.secretnotes;
import android.app.*;import android.os.*;import android.content.*;import android.graphics.Color;import android.view.*;import android.widget.*;
public class PinActivity extends Activity {
 EditText pin,confirm; SecurePrefs secure; boolean setup;
 public void onCreate(Bundle b){super.onCreate(b);secure=new SecurePrefs(this);setup=!secure.hasPin(); setContentView(R.layout.activity_pin); TextView heading=findViewById(R.id.pinHeading);heading.setText(setup?"Create your PIN":"Welcome back");confirm=findViewById(R.id.pinConfirm);confirm.setVisibility(setup?View.VISIBLE:View.GONE);pin=findViewById(R.id.pin);findViewById(R.id.pinButton).setOnClickListener(v->submit());}
 void submit(){String a=pin.getText().toString().trim();if(a.length()!=4||!a.matches("\\d{4}")){pin.setError("Enter exactly 4 digits");return;}if(setup){if(!a.equals(confirm.getText().toString())){confirm.setError("PINs do not match");return;}secure.setPin(a);open();}else if(secure.verify(a))open();else pin.setError("Incorrect PIN");}
 void open(){startActivity(new Intent(this,MainActivity.class));finish();}
}
