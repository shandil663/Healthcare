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
 String[] items={"Distt. Hospital, Amritsar", "SDH, Ajnala", "SDH, Baba Bakala", "CHC Lopoke", "CHC Manawala", "CHC Tarsikka", "CHC Majitha", "Community Health Centre, Bhadaur", "Community Health Centre, Dhanaula", "Community Health Centre, Tapa", "District Hospital, Barnala", "Women & Child Hospital, Bathinda", "Sub Divisional Hospital, Talwandi Sabo", "Community Health Centre, Sangat", "Community Health Centre, Nathana", "Community Health Centre, Maur Mandi", "Community Health Centre, Bhucho Mandi", "Community Health Centre, Mehraj", "Community Health Centre, Bhagat", "Sub Divisional Hospital Ram Pura Phul","Community Health Centre, Bhatinda", "Community Health Centre, Nathana", "Community Health Centre, Maur Mandi", "Community Health Centre, Bhucho Mandi", "Community Health Centre, Mehraj", "Community Health Centre, Bhagat", "Sub Divisional Hospital, Rampura Phul", "SDH, Bariwala", "SDH, Malout", "CHC, Lambi", "CHC, Gidderbaha", "SDH, Fazilka", "SDH, Abohar", "Civil Hospital, Muktsar", "SDH, Gurusar Sadhar", "SDH, Jagraon", "CHC, Raikot", "SDH, Moga", "SDH, Nihal Singh Wala", "CHC, Dharamkot",};
    String[] items1={"Dr. Rajesh Sharma", "Dr. Priya Patel", "Dr. Alok Kumar", "Dr. Nandini Singh", "Dr. Vikram Desai", "Dr. Anjali Gupta", "Dr. Arjun Reddy", "Dr. Meera Khanna", "Dr. Sanjay Joshi", "Dr. Pooja Verma", "Dr. Ravi Menon", "Dr. Naina Kapoor", "Dr. Rohit Singhania", "Dr. Kavita Rastogi", "Dr. Anil Agarwal", "Dr. Ananya Das", "Dr. Sameer Malhotra", "Dr. Sneha Choudhury", "Dr. Suresh Iyer", "Dr. Nisha Sharma", "Dr. Prakash Kapoor", "Dr. Ayesha Pandey", "Dr. Arvind Rajput", "Dr. Deepika Rao", "Dr. Raghav Mehta", "Dr. Shalini Gupta", "Dr. Rahul Verma", "Dr. Kiran Nair", "Dr. Shashi Joshi", "Dr. Neelam Malhotra", "Dr. Aditya Chauhan", "Dr. Simran Ahuja", "Dr. Vikas Kapoor", "Dr. Diya Reddy", "Dr. Abhishek Patel", "Dr. Preeti Sharma", "Dr. Rajat Khanna", "Dr. Karishma Kumar", "Dr. Ajay Singh", "Dr. Anushka Bhatia",};
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

//                    Toast.makeText(appointmentbooking.this, "Appointment booked", Toast.LENGTH_SHORT).show();
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