package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthcare.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class appointmentbooking extends AppCompatActivity {


    Button btn;
      CardView card;


    EditText edit1,edit2,edit3;

    ActivityMainBinding binding;
    FirebaseDatabase db;

    FirebaseAuth oth;
    DatabaseReference ref;

    String HOSNAME,DOCNAME,ILL,TIME,NAME,AGE;
 String[] items={"Government hospital1","Government hospital2","Government hospital3","Government hospital4","Government hospital5","Government hospital6","Government hospital7",};
    String[] items1={"Dr. Gaurav Taneja","Dr. Rudra Verma","Dr. Bhomik","Dr. Sahil Shandil","Dr. Ayushi Agarwal","Dr. Tanisha","Dr. Akshat",};
    String[] items2={"9:00am to 10:00am","10:00am to 11:00am","11:00am to 12:00pm","1:00pm to 2:00pm","2:00pm to 3:00pm"};
 AutoCompleteTextView autoCompleteTxt,autoCompleteTxt1,autoCompleteTxt2;
 ArrayAdapter<String> adapterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentbooking);

        card=findViewById(R.id.cardbackfromappoint);
        btn=findViewById(R.id.appoint);
        autoCompleteTxt=findViewById(R.id.auto_complete_txt);
        autoCompleteTxt1=findViewById(R.id.auto_complete_txt1);
        autoCompleteTxt2=findViewById(R.id.auto_complete_txt3);
        edit1=findViewById(R.id.editTextText);
        edit2=findViewById(R.id.editTextText1);
        edit3=findViewById(R.id.editTextText3);
        oth=FirebaseAuth.getInstance();
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HOSNAME=autoCompleteTxt.getText().toString();
                DOCNAME=autoCompleteTxt1.getText().toString();
                TIME=autoCompleteTxt2.getText().toString();
                ILL=edit1.getText().toString();
                NAME=edit2.getText().toString();
                AGE=edit3.getText().toString();

                if(!HOSNAME.isEmpty()&&!DOCNAME.isEmpty()&&!TIME.isEmpty()&&!ILL.isEmpty()&&!NAME.isEmpty()&&!AGE.isEmpty()){
                    Patients patients=new Patients(HOSNAME,DOCNAME,ILL,TIME,NAME,AGE);
                    db=FirebaseDatabase.getInstance();
                    ref=db.getReference("Patients").child(oth.getCurrentUser().getUid());
                    ref.setValue(patients).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(appointmentbooking.this, "Appointment booked", Toast.LENGTH_SHORT).show();

                        }
                    });

                    Toast.makeText(appointmentbooking.this, "Appointment booked", Toast.LENGTH_SHORT).show();
                    Intent booked=new Intent(getApplicationContext(), bookedappointement.class);
//                    booked.putExtra("hosname",HOSNAME);
//                    booked.putExtra("docname",DOCNAME);
//                    booked.putExtra("ill",ILL);
//                    booked.putExtra("name",NAME);
//                    booked.putExtra("age",AGE);
//                    booked.putExtra("time",TIME);
                    startActivity(booked);
                    finish();

                }

                else{
                    Toast.makeText(appointmentbooking.this, "Enter required filled", Toast.LENGTH_SHORT).show();
                }

            }
        });

        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item =adapterView.getItemAtPosition(i).toString();
            }
        });
        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,items1);
        autoCompleteTxt1.setAdapter(adapterItems);
        autoCompleteTxt1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item =adapterView.getItemAtPosition(i).toString();
            }
        });
        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,items2);
        autoCompleteTxt2.setAdapter(adapterItems);
        autoCompleteTxt2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item =adapterView.getItemAtPosition(i).toString();
            }
        });

    }
}