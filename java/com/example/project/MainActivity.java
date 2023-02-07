package com.example.project;

import androidx.appcompat.app.AppCompatActivity; import android.graphics.Paint;
import android.os.Bundle; import android.app.Activity;
 import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
      Button bt1;
      Button bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1=(Button)findViewById(R.id.button);
        bt2=(Button)findViewById(R.id.button2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);}
    @Override
    public void onClick(View view ){
              if(view==bt1) {
                  Intent in=new Intent(this,REGISTER.class);
                  startActivity(in);
              }
              else {
                  Intent in=new Intent(this,LOGIN.class);
                  startActivity(in);
              }
      }
    }