package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Medicinedetails extends AppCompatActivity {
ImageView img;
String str;
TextView txt1,txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinedetails);

        img=findViewById(R.id.medimg);
        txt1=findViewById(R.id.medname);
        txt2=findViewById(R.id.medprice);
        str=getIntent().getStringExtra("imageresource");
        Glide.with(Medicinedetails.this).load(str).into(img);
        txt1.setText(getIntent().getStringExtra("title"));
        txt2.setText(getIntent().getStringExtra("price"));
    }
}