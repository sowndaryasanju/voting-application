package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
public class REGISTER extends AppCompatActivity implements View.OnClickListener{
     Button b;
     EditText fn,ln,i,p,cp,ph;
     SQLiteDatabase db;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_register);
       b = findViewById(R.id.submit);
       b.setOnClickListener(this);

   }
    @Override
    public void onClick(View view ) {
        fn = findViewById(R.id.efname);
        ln = findViewById(R.id.elname);
        i = findViewById(R.id.eid);
        ph = findViewById(R.id.ephno);
        p = findViewById(R.id.epass);
        cp = findViewById(R.id.epassc);
        if(fn.getText().toString().equals("") || ln.getText().toString().equals("")|| i.getText().toString().equals("")||ph.getText().toString().equals("")||p.getText().toString().equals("")||cp.getText().toString().equals("")){
           al();
           return;
        }
        if(p.getText().toString().equals(cp.getText().toString())==false){
            mismatch();
            return;
        }
        db = openOrCreateDatabase("user", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS voterinfo(fname varchar(25),lname varchar(25),id varchar(25),phno varchar(10),pass varchar(30),voted varchar(1));");
        db.execSQL("INSERT INTO voterinfo (fname,lname,id,phno,pass,voted) VALUES('"
                +fn.getText().toString()+"','"+ ln.getText().toString()+"','"+ i.getText().toString()+"','"
                +ph.getText().toString()+"','"+p.getText().toString()+"','0');");
       AlertDialog.Builder builder= new AlertDialog.Builder(this);
       Cursor c = db.rawQuery("SELECT * FROM voterinfo",null);
       c.moveToFirst();
        Log.d("msg",c.getString(0));
        Log.d("msg",c.getString(1));
        Log.d("msg",c.getString(2));
        Log.d("msg",c.getString(3));
        Log.d("msg",c.getString(4));
        Log.d("msg",c.getString(5));


        builder.setTitle("Sucess");
        builder.setMessage("You have registered sucessfully!!!!!");
        builder.setCancelable(true);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
        Intent in=new Intent(this,MainActivity.class);
        startActivity(in);
    }
    public void al(){
        Log.d("msg","Not filled");
       AlertDialog.Builder builder= new AlertDialog.Builder(this);
       builder.setTitle("No entry");
       builder.setMessage("You have not filled some field!!!");
        builder.setCancelable(true);
       AlertDialog alertDialog=builder.create();
       alertDialog.show();
    }
    public void mismatch(){
        Log.d("msg","Password mismatch");
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Password mismatch");
        builder.setCancelable(true);
        builder.setMessage("The entered password and confirm password does not match!!!");
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

}