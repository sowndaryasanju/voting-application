package com.example.project;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
public class VOTE extends AppCompatActivity {
    RadioButton b1,b2,b3,b4;
    SQLiteDatabase db;
    TextView txt;
    String selected;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        Intent i=getIntent();
        String str=i.getStringExtra("voted");
        txt=(TextView) findViewById(R.id.textView);
        b1=(RadioButton) findViewById(R.id.radioButton);
        b2=(RadioButton) findViewById(R.id.radioButton2);
        b3=(RadioButton) findViewById(R.id.radioButton3);
        b4=(RadioButton) findViewById(R.id.radioButton4);
        db=openOrCreateDatabase("Polling",Context.MODE_NO_LOCALIZED_COLLATORS,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS poll (partyName varchar(25),voteCount int);");
        db.execSQL("INSERT INTO poll(partyName,voteCount)VALUES('XDMA',0);");
        db.execSQL("INSERT INTO poll(partyName,voteCount)VALUES('SKRS',0);");
        db.execSQL("INSERT INTO poll(partyName,voteCount)VALUES('CMDS',0);");
        db.execSQL("INSERT INTO poll(partyName,voteCount)VALUES('NOTA',0);");
        submit=(Button) findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){

                if(b1.isChecked()){
                    selected=b1.getText().toString();
                } else if(b2.isChecked()){
                    selected=b2.getText().toString();
                } else if(b3.isChecked()) {
                    selected = b3.getText().toString();
                } else if(b4.isChecked()) {
                    selected = b4.getText().toString();
                }
                db.execSQL("CREATE TABLE IF NOT EXISTS poll (partyName varchar(25),voteCount int);");
                if(selected==b1.getText()){
                    db.execSQL("UPDATE poll SET voteCount=voteCount+1 WHERE partyName='XDMA';");
                } else if(selected==b2.getText()){
                    db.execSQL("UPDATE poll SET voteCount=voteCount+1 WHERE partyName='SKRS';");
                } else if(selected==b3.getText()){
                    db.execSQL("UPDATE poll SET voteCount=voteCount+1 WHERE partyName='CMDS';");
                } else if(selected==b4.getText()){
                    db.execSQL("UPDATE poll SET voteCount=voteCount+1 WHERE partyName='NOTA';");
                }
               db=openOrCreateDatabase("user", Context.MODE_PRIVATE, null);
                db.execSQL("UPDATE voterinfo SET voted='1' WHERE phno="+str+";");
                Toast.makeText(getApplicationContext(),selected,Toast.LENGTH_LONG).show();
                change();
            }
        });
    }
    public void change(){
        Intent i=new Intent(this,THANK.class);
        startActivity(i);
    }
}