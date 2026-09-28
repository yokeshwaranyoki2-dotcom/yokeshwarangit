package com.secretnotes;
import android.app.*;import android.os.*;import android.content.*;import android.text.*;import android.view.*;import android.widget.*;import androidx.recyclerview.widget.*;import java.text.*;import java.util.*;
public class MainActivity extends Activity {
 NotesDb db; NoteAdapter adapter; EditText search;
 public void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);db=new NotesDb(this);search=findViewById(R.id.search);RecyclerView list=findViewById(R.id.notesList);adapter=new NoteAdapter(this,n->startActivity(new Intent(this,EditorActivity.class).putExtra("id",n.id)));list.setLayoutManager(new LinearLayoutManager(this));list.setAdapter(adapter);search.addTextChangedListener(new TextWatcher(){public void beforeTextChanged(CharSequence s,int a,int c,int d){}public void onTextChanged(CharSequence s,int a,int b,int c){load(s.toString());}public void afterTextChanged(Editable e){}});findViewById(R.id.addNote).setOnClickListener(v->startActivity(new Intent(this,EditorActivity.class)));findViewById(R.id.settings).setOnClickListener(v->startActivity(new Intent(this,SettingsActivity.class)));findViewById(R.id.lock).setOnClickListener(v->{startActivity(new Intent(this,PinActivity.class));finish();});load("");}
 void load(String q){adapter.setNotes(db.all(q));}
 protected void onResume(){super.onResume();if(db!=null)load(search==null?"":search.getText().toString());}
}
