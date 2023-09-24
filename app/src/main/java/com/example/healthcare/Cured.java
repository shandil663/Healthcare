package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Cured extends AppCompatActivity {
RecyclerView cycleview;
CardView card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cured);
        card=findViewById(R.id.curedback);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cured.this,medicalhistory.class));
                finish();
            }
        });
        cycleview=findViewById(R.id.cycleview);
        List<item>listitems=new ArrayList<item>();
        listitems.add(new item("Typhoid","20 Feb 2023 to 15 March 2023"));
        listitems.add(new item("Jaundice","2 June 2023 to 15 July 2023"));
        listitems.add(new item("Stone","20 Feb 2023 to 15 March 2023"));
        listitems.add(new item("Heart Issue","10 Nov 2022 to 15 Dec 2022"));
        listitems.add(new item("Kidney failure","20 Feb 2023 to 15 March 2023"));
        listitems.add(new item("Common cold","20 Feb 2023 to 15 March 2023"));
        listitems.add(new item("Dengue","20 Feb 2023 to 15 March 2023"));
        listitems.add(new item("Normal Checkup","2 Jan 2018 to 15 Jan 2018"));
        cycleview.setLayoutManager(new LinearLayoutManager(this));
        cycleview.setAdapter(new MyAdpater(getApplicationContext(),listitems));
    }
}