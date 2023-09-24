package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class testreports extends AppCompatActivity {
private RecyclerView cycle;
private ArrayList <Integer> array;
private RecyclerViewAdpater recyclerViewAdpater;
CardView card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testreports);

        cycle=findViewById(R.id.cycle1);
        card=findViewById(R.id.testreportsback);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(testreports.this, medicalhistory.class));
                finish();
            }
        });

        array=new ArrayList<>();
        array.add(R.drawable.img_14);
        array.add(R.drawable.img_14);
        array.add(R.drawable.img_14);
        array.add(R.drawable.img_14);
        array.add(R.drawable.img_14);
        array.add(R.drawable.img_14);
        array.add(R.drawable.img_14);
        array.add(R.drawable.img_14);
        array.add(R.drawable.img_14);



        recyclerViewAdpater = new RecyclerViewAdpater(this,array);
        cycle.setLayoutManager(new LinearLayoutManager(this));
        cycle.setAdapter(recyclerViewAdpater);

    }
}