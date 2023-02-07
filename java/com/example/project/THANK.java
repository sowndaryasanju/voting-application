package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class THANK extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank);

    Button bt=(Button) findViewById(R.id.button3);
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        mAuth.signOut();

        bt.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
        change();
        }
    });
}
public void change(){
    Intent in=new Intent(this,MainActivity.class);
    startActivity(in);
}
}