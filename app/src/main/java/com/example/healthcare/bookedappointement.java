package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class bookedappointement extends AppCompatActivity {

    TextView txt1,txt2,txt3,txt4,txt5;


    FirebaseAuth uidauth;
    CardView card;

    DatabaseReference refer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookedappointement);

        txt1=findViewById(R.id.namepatient);
        txt2=findViewById(R.id.agepatient);
        txt3=findViewById(R.id.docnameid);
        txt4=findViewById(R.id.textslotid);
        txt5=findViewById(R.id.illnessdomain);
        card=findViewById(R.id.cardfrombook);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bookedappointement.this,MainActivity.class));
                finish();
            }
        });
        uidauth=FirebaseAuth.getInstance();
        Toast.makeText(bookedappointement.this, "Loading Data, Please wait", Toast.LENGTH_LONG).show();

        readata(uidauth.getCurrentUser().getUid());

//        Intent getvalue=new Intent();
//       t1= getvalue.getStringExtra("name").toString();
//       t2=getvalue.getStringExtra("age").toString();
//       t3=getvalue.getStringExtra("docname").toString();
//       t4=getvalue.getStringExtra("time").toString();
//       t5=getvalue.getStringExtra("hosname").toString();
//
//
//       txt1.setText(t1);
//       txt2.setText(t2);
//       txt3.setText(t3);
//       txt4.setText(t4);
//       txt5.setText(t5);


    }

    private void readata(String uid) {

        refer= FirebaseDatabase.getInstance().getReference("Patients");
        refer.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()){
if(task.getResult().exists()){
    Toast.makeText(bookedappointement.this, "Data Loaded Successfully", Toast.LENGTH_LONG).show();

    DataSnapshot data= task.getResult();
    String patname= String.valueOf(data.child("name").getValue());
    String patage= String.valueOf(data.child("age").getValue());
    String Docname= String.valueOf(data.child("docname").getValue());
    String illname= String.valueOf(data.child("ill").getValue());
    String TIMEslot= String.valueOf(data.child("time").getValue());

    txt1.setText(patname);
    txt2.setText(patage);
    txt3.setText(Docname);
    txt4.setText(TIMEslot);
    txt5.setText(illname);

}

else{
    Toast.makeText(bookedappointement.this, "No user found", Toast.LENGTH_SHORT).show();
}
                }

                else{
                    Toast.makeText(bookedappointement.this, "No data available", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}