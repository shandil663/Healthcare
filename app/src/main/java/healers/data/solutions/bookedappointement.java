package healers.data.solutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import healers.data.solutions.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class bookedappointement extends AppCompatActivity {

    TextView txt1, txt2, txt3, txt4, txt5;
    TextView docidhere, speciality;

    FirebaseAuth uidauth;
    CardView card;

    DatabaseReference refer, mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookedappointement);
        FirebaseApp.initializeApp(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        txt1 = findViewById(R.id.namepatient);
        txt2 = findViewById(R.id.agepatient);
        txt3 = findViewById(R.id.docnameid);
        docidhere = findViewById(R.id.docidhere);
        speciality = findViewById(R.id.sepciality);
        txt4 = findViewById(R.id.textslotid);
        txt5 = findViewById(R.id.illnessdomain);
        card = findViewById(R.id.cardfrombook);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bookedappointement.this, MainActivity.class));
                finish();
            }
        });
        uidauth = FirebaseAuth.getInstance();
//        Toast.makeText(bookedappointement.this, "Loading Data, Please wait", Toast.LENGTH_LONG).show();

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

        refer = FirebaseDatabase.getInstance().getReference("Patients");
        refer.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {


                        DataSnapshot data = task.getResult();
                        String patname = String.valueOf(data.child("name").getValue());
                        String patage = String.valueOf(data.child("age").getValue());
                        String Docname = String.valueOf(data.child("docname").getValue());
                        String illname = String.valueOf(data.child("ill").getValue());
                        String TIMEslot = String.valueOf(data.child("time").getValue());
                        fetchDoctorSpecialty(Docname);
                        txt1.setText(patname);
                        txt2.setText(patage);
                        txt3.setText(Docname);
                        txt4.setText(TIMEslot);
                        txt5.setText(illname);

                    } else {
                        Toast.makeText(bookedappointement.this, "No user found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(bookedappointement.this, "No data available", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void fetchDoctorSpecialty(String doctorName) {
        List<String> hospitals = new ArrayList<>();
        hospitals.add("hospital_12345");
//        hospitals.add("hospital_67890");
//        hospitals.add("hospital_13579");

        for (String hospital : hospitals) {
            mDatabase.child(hospital).child("doctors").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot doctorSnapshot : dataSnapshot.getChildren()) {
                        String docid = doctorSnapshot.getKey();
                        String name = doctorSnapshot.child("doctor_name").getValue(String.class);
                        if (name != null && name.equals(doctorName)) {
                            String specialty = doctorSnapshot.child("speciality").getValue(String.class);
                            if (specialty != null && docid != null) {
                                docidhere.setText(docid);
                                speciality.setText(specialty);
                                Log.d("Specialty", "Specialty of " + doctorName + " is " + specialty);
//                                Toast.makeText(bookedappointement.this, "Specialty of " + doctorName + " is " + specialty, Toast.LENGTH_LONG).show();
                                return;
                            }


                        }
                    }
                    Log.d("Specialty", "Doctor " + doctorName + " not found in " + hospital);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w("Fetch Error", "loadPost:onCancelled", databaseError.toException());
                }
            });
        }
    }
}