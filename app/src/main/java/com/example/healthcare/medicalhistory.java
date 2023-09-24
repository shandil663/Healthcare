package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class medicalhistory extends AppCompatActivity {
CardView card,reports,cured;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicalhistory);
        card=findViewById(R.id.bacfrommed);
        reports=findViewById(R.id.Test);
        cured=findViewById(R.id.cureddata);
        cured.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(medicalhistory.this, Cured.class));
            }
        });

        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(medicalhistory.this, testreports.class));

            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(medicalhistory.this,MainActivity.class));
                finish();
            }
        });
    }
}