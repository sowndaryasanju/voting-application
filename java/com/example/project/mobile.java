package com.example.project;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class mobile extends AppCompatActivity {
    Intent i;
    String str,mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    EditText t;
    Button b;
    FirebaseAuth mAuth;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        b=(Button)findViewById(R.id.button4);
        i=getIntent();
        t=(EditText)findViewById(R.id.editTextTextPersonName);
        str=i.getStringExtra("num");
        mAuth=FirebaseAuth.getInstance();
        PhoneAuthOptions options=PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber("+91"+str)
                .setTimeout(60L,TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(t.getText().toString()==""){
                    tv.setText("Enter OTP");
                }
                else{
                    verify(t.getText().toString());
                }
            }
        });


                }
                private PhoneAuthProvider.OnVerificationStateChangedCallbacks
    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {

            Log.d(TAG, "onVerificationCompleted:" + credential);
            final String code=credential.getSmsCode();
            if(code!=null){
                verify(code);
            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            tv=(TextView) findViewById(R.id.textView5);
            tv.setText("Verification Fail");


        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                @NonNull PhoneAuthProvider.ForceResendingToken token) {
            tv=(TextView) findViewById(R.id.textView5);
            tv.setText("OTP sent");
            mVerificationId = verificationId;
            mResendToken = token;
        }
    };
    public void verify(String code){
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(mVerificationId,code);
        signinbyCredentials(credential);
    }
    public void signinbyCredentials(PhoneAuthCredential credential){
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            change();
                        }
                    }
                });
    }
    public void change(){
        Intent i=new Intent(this,VOTE.class);
        i.putExtra("voted", str);
        startActivity(i);
    }
    }
