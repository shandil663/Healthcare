package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
CardView card,viewappoint,meddata,nfctag,emertag,buymed,blood;
TextView txt;
ImageView img;

FirebaseAuth ooth;
DatabaseReference refy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nfctag=findViewById(R.id.nfctag);
        emertag=findViewById(R.id.emertag);
blood=findViewById(R.id.bloodcheck);
blood.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(), Blood.class));
    }
});
        buymed=findViewById(R.id.buymed);
        buymed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Buydrugs.class));
            }
        });
        img=findViewById(R.id.menunormal);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.openmenu);
                startActivity(new Intent(MainActivity.this, settings.class));
            }
        });
        nfctag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NFC.class));
            }
        });

        emertag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Emergency.class));
            }
        });



        ooth=FirebaseAuth.getInstance();
        viewappoint=findViewById(R.id.viewappointid);

        card=findViewById(R.id.booking);
        meddata=findViewById(R.id.showmedicaldata);

        meddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), medicaldetails.class));
            }
        });
        viewappoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Please wait for few seconds", Toast.LENGTH_LONG).show();
                refy= FirebaseDatabase.getInstance().getReference("Patients");
                refy.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(ooth.getCurrentUser().getUid())){

                            startActivity(new Intent(getApplicationContext(),bookedappointement.class));
                        }

                        else{
                            Toast.makeText(MainActivity.this, "Book appointment First", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

        });



        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent book=new Intent(getApplicationContext(), appointmentbooking.class);
                startActivity(book);
            }
        });
    }
}