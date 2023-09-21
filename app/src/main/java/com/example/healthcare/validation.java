package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class validation extends AppCompatActivity {
Button btn;
TextView dummy;
CardView card;

FirebaseAuth mAuth;
    String phonenumber;
    String otpid;
EditText t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_validation);

        card=findViewById(R.id.cardbackfromvali);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(validation.this, Login.class));
                finish();
            }
        });
        dummy=findViewById(R.id.dummylogin);
        dummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dumy=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(dumy);
            }
        });
        phonenumber=getIntent().getStringExtra("mobile").toString();
        btn=findViewById(R.id.verify);
      t2=(EditText)findViewById(R.id.textotp);

mAuth=FirebaseAuth.getInstance();
      alreadysim();
      
 btn.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         if(t2.getText().toString().isEmpty()){
             Toast.makeText(getApplicationContext(), "Blank filled can't be processed", Toast.LENGTH_SHORT).show();
         }
         else if(t2.getText().toString().length()!=6){
             Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();
         }
         else{
             PhoneAuthCredential credential=PhoneAuthProvider.getCredential(otpid,t2.getText().toString());
             signInWithPhoneAuthCredential(credential);
         }
     }
 });
    }

    private void alreadysim() {

      PhoneAuthProvider.getInstance().verifyPhoneNumber(
              phonenumber,
              60,
              TimeUnit.SECONDS,
              this,
              new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                  @Override
                  public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                      Toast.makeText(validation.this, "recieve", Toast.LENGTH_SHORT).show();
                      signInWithPhoneAuthCredential(phoneAuthCredential);
                  }

                  @Override
                  public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                   otpid=s;

                  }

                  @Override
                  public void onVerificationFailed(@NonNull FirebaseException e) {
                      Toast.makeText(validation.this,e.getMessage(), Toast.LENGTH_LONG).show();

                  }
              }
      );
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(validation.this, " Login Successful", Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(validation.this, MainActivity.class));
                          
                          
                        } else {
                            Toast.makeText(validation.this, "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}