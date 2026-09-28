package com.secretnotes;
import android.app.*;import android.os.*;import android.content.*;import android.view.*;import android.widget.*;
public class EditorActivity extends Activity {
 NotesDb db;long id;EditText title,body;
 public void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_editor);db=new NotesDb(this);id=getIntent().getLongExtra("id",0);title=findViewById(R.id.noteTitle);body=findViewById(R.id.noteBody);if(id!=0){Note n=db.get(id);if(n!=null){title.setText(n.title);body.setText(n.body);}}findViewById(R.id.save).setOnClickListener(v->save());findViewById(R.id.delete).setOnClickListener(v->confirmDelete());}
 void save(){String t=title.getText().toString().trim(),b=body.getText().toString().trim();if(t.isEmpty()){title.setError("Title is required");return;}if(b.isEmpty()){body.setError("Note cannot be empty");return;}db.save(id,t,b);Toast.makeText(this,"Note saved",Toast.LENGTH_SHORT).show();finish();}
 void confirmDelete(){if(id==0){finish();return;}new AlertDialog.Builder(this).setTitle("Delete note?").setMessage("This note will be permanently deleted.").setNegativeButton("Cancel",null).setPositiveButton("Delete",(d,w)->{db.delete(id);finish();}).show();}
}
