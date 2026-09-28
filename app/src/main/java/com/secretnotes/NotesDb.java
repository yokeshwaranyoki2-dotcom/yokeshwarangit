package com.secretnotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.*;

public class NotesDb extends SQLiteOpenHelper {
    private static final String DB="notes.db";
    public NotesDb(Context c){super(c,DB,null,1);}
    public void onCreate(SQLiteDatabase db){db.execSQL("CREATE TABLE notes (_id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT NOT NULL,body TEXT NOT NULL,updated INTEGER NOT NULL)");}
    public void onUpgrade(SQLiteDatabase db,int oldV,int newV){}
    public long save(long id,String title,String body){ContentValues v=new ContentValues();v.put("title",title);v.put("body",body);v.put("updated",System.currentTimeMillis());if(id==0)return getWritableDatabase().insert("notes",null,v);return getWritableDatabase().update("notes",v,"_id=?",new String[]{String.valueOf(id)});}
    public void delete(long id){getWritableDatabase().delete("notes","_id=?",new String[]{String.valueOf(id)});}
    public ArrayList<Note> all(String query){ArrayList<Note> r=new ArrayList<>();Cursor c=getReadableDatabase().query("notes",null,"title LIKE ? OR body LIKE ?",new String[]{"%"+query+"%","%"+query+"%"},null,null,"updated DESC");while(c.moveToNext())r.add(new Note(c.getLong(0),c.getString(1),c.getString(2),c.getLong(3)));c.close();return r;}
    public Note get(long id){Cursor c=getReadableDatabase().query("notes",null,"_id=?",new String[]{String.valueOf(id)},null,null,null);Note n=null;if(c.moveToFirst())n=new Note(c.getLong(0),c.getString(1),c.getString(2),c.getLong(3));c.close();return n;}
}
