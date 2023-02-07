package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class count extends AppCompatActivity {
    TextView a,b,m,d;
    SQLiteDatabase db;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        a=(TextView) findViewById(R.id.vote1);
        b=(TextView) findViewById(R.id.vote2);
        m=(TextView) findViewById(R.id.vote3);
        d=(TextView) findViewById(R.id.vote4);
        db=openOrCreateDatabase("polling", Context.MODE_NO_LOCALIZED_COLLATORS, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS poll(partyName varchar(25),voteCount int);");
        c=db.rawQuery("SELECT * FROM poll WHERE partyName LIKE '%XDMA%';",null);
        if(c.getCount()>0) {
            c.moveToFirst();
            String x, y, z, p;
            p = c.getString(1).toString();
            c = db.rawQuery("SELECT * FROM poll WHERE partyName LIKE '%SKRS%';", null);
            c.moveToFirst();
            x = c.getString(1).toString();
            c = db.rawQuery("SELECT * FROM poll WHERE partyName LIKE '%CMDS%';", null);
            c.moveToFirst();
            y = c.getString(1).toString();
            c = db.rawQuery("SELECT * FROM poll WHERE partyName LIKE '%NOTA%';", null);
            c.moveToFirst();
            z = c.getString(1).toString();
            a.setText(z);
            b.setText(x);
            m.setText(y);
            d.setText(p);
        }
        else{
            a.setText("NULL");



        }
    }
}