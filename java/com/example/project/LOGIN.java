package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
public class LOGIN extends AppCompatActivity implements View.OnClickListener{
    EditText et1,et2;
    Button bt;
    SQLiteDatabase db;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et1=(EditText)findViewById(R.id.uid);
        et2=(EditText)findViewById(R.id.pass);
        bt=(Button)findViewById(R.id.logbutton);
        bt.setOnClickListener(this);
        t=(TextView)findViewById(R.id.title);
    }
    public void onClick(View view){
        if(et1.getText().toString().equals("")){
            t.setText("Enter user id");
            return;
        }
        if(et2.getText().toString().equals("")){
            t.setText("Enter password");
            return;
        }
        if(et1.getText().toString().equals("75757")){
            Log.d("Admin","match 1");
            if(et2.getText().toString().equals("1234")){
                Log.d("Admin","match 2");
                Intent in=new Intent(this,admin.class);
                startActivity(in);
                return;
            }
            else{
                Log.d("msg","1234");
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setTitle("Error");
                builder.setCancelable(true);
                builder.setMessage("Password does not match!!!");
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                return;
            }
        }
        db = openOrCreateDatabase("user", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM voterinfo WHERE id="+et1.getText().toString()+";",null);
        c.moveToFirst();
        String str=c.getString(3).toString();
        if(et2.getText().toString().equals(c.getString(4).toString())){
            Log.d("msg","logging in");
            if(c.getString(5).toString().equals("0")){
                Intent in=new Intent(this,mobile.class);
                in.putExtra("num",str);
                startActivity(in);}
            else{
                Log.d("error",c.getString(5).toString());
                Intent in=new Intent(this,AlreadyVoted.class);
                startActivity(in);
            }

        }
        else{
            Log.d("msg","invalid");
            Log.d("msg",c.getString(0));
            Log.d("msg",et2.getText().toString());
            t.setText("Invalid user name or password");
        }
    }

}