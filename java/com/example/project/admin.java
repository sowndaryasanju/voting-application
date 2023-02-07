package com.example.project;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;

public class admin extends AppCompatActivity implements View.OnClickListener{
    SQLiteDatabase db;
    Button bt;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        bt=(Button)findViewById(R.id.erase);
        b=(Button)findViewById(R.id.count);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                call();

            }
        });
        bt.setOnClickListener(this);
    }
    public void call(){
        Intent i=new Intent(this, count.class);
        startActivity(i);
    }
    public void onClick(View view){
        if(view==bt){
        db = openOrCreateDatabase("user", Context.MODE_PRIVATE, null);
        db.execSQL("DELETE FROM voterinfo;");
        db.execSQL("VACUUM;");
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Deleted");
        builder.setCancelable(true);
        builder.setMessage("All records of voters has been deleted successfully!!!");
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
        }
    }
}