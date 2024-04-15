package com.example.healthcare;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.model.AdapterClass;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Blood extends AppCompatActivity {

    String[] sates={"Delhi","Punjab","Uttar Pradesh","Himachal Pradesh","Andaman and Nicobar Islands", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chandigarh", "Chhattisgarh", "Dadra and Nagar Haveli and Daman and Diu", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Ladakh", "Lakshadweep", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Puducherry", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"};

    String [] DelhiCity ={"Aali", "Alipur", "Asola", "Aya Nagar", "Babarpur", "Bakhtawarpur", "Bakkar Wala", "Bankauli", "Bankner", "Bapraula", "Baqiabad", "Barwala", "Bawana", "Begum Pur", "Bhalswa Jahangirpur", "Bhati", "Bhor Garh", "Burari", "Chandan Hola", "Chattarpur", "Chhawala", "Chilla Saroda Bangar", "Chilla Saroda Khadar", "Daryapur Kalan", "Dayalpur", "Dallopura", "Dindarpur", "Deoli", "Dera Mandi", "Fatehpur Beri", "Gharonda Neemka Bangar (also known as Patparganj)", "Gharoli", "Gheora", "Gokalpur", "Ghitorni", "Hastsal", "Ibrahimpur", "Jaitpur", "Jiwanpur (also called Johripur)", "Jaffrabad", "Jharoda Kalan", "Jonapur", "Jharoda Majra Burari", "Kanjhawala", "Kamalpur Majra Burari", "Kapas Hera", "Karala", "Karawal Nagar", "Kair", "Khanpur Dhani", "Khajoori Khas", "Khera", "Khera Kalan", "Khera Khurd", "Kirari Suleman Nagar", "Kondli", "Kotla Mahigiran", "Kusumpur", "Ladpur", "Libaspur", "Maidan Garhi", "Mehrauli", "Mukhmelpur", "Moradabad Pahari", "Malikpur Kohi alias Rangpuri", "Mithepur", "Molarband", "Mirpur Turk", "Mubarak Pur Dabas", "Mustafabad", "Mohammad Pur Majri", "Mandoli", "Mukandpur", "Mundka", "Mitraon", "Nilothi", "Nangloi Jat", "Nithari", "Neb Sarai", "Nangli Sakrawati", "Pooth Kalan", "Pooth Khurd", "Pul Pehlad", "Prahlad Pur Bangar", "Qadipur", "Quammruddin Nagar", "Rajapur Khurd", "Rajokri", "Rani Khera", "Roshanpura (also called Dichaon Khurd)", "Sultanpur Majra", "Saidabad", "Shakarpur Baramad", "Sadatpur Gujran", "Siraspur", "Sahibabad Daulatpur", "Shafipur Ranhola", "Tikri Kalan", "Sambhalka", "Sultanpur", "Saidul Azaib", "Taj Pul", "Tukhmirpur", "Tilangpur Kotla", "Tigri", "Tikri Khurd", "Ziauddinpur"};

    String [] UP={"Lucknow", "Kanpur", "Prayagraj (formerly Allahabad)", "Agra", "Varanasi", "Meerut", "Ghaziabad", "Bareilly", "Aligarh", "Moradabad", "Mathura", "Jhansi", "Gorakhpur", "Noida (Greater Noida)"};

    String [] Punjab={"Amritsar", "Ludhiana", "Jalandhar", "Patiala", "Bathinda", "Mohali (officially Sahibzada Ajit Singh Nagar)", "Pathankot", "Batala", "Phagwara", "Hoshiarpur", "Moga", "Kapurthala", "Sangrur"};

    String [] Bloodgrp={"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
    AutoCompleteTextView state,city,bloodgrp;
    ArrayAdapter<String> adapterItems;

    Button fetch;

    LottieAnimationView lottieAnimationView;

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_blood);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.blood), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recview); // Replace with your recycler view id
fetch=findViewById(R.id.fetchdoc);
lottieAnimationView=findViewById(R.id.bloodload);

fetch.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(!state.getText().toString().isEmpty()&&!city.getText().toString().isEmpty()&&!bloodgrp.getText().toString().isEmpty()){
        recyclerView.setVisibility(View.GONE);
lottieAnimationView.setVisibility(View.VISIBLE);}
        Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                lottieAnimationView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                if(!state.getText().toString().isEmpty()&&!city.getText().toString().isEmpty()&&!bloodgrp.getText().toString().isEmpty()){

                    if(state.getText().toString().equals("Punjab")&&city.getText().toString().equals("Amritsar")&&bloodgrp.getText().toString().equals("A+")){
                        List<HashMap<String, String>> dataList = new ArrayList<>();
                        HashMap<String, String> data1 = new HashMap<>();
                        data1.put("userId", "Ram jain");
                        data1.put("domain", "A+");
                        data1.put("field1", "Available");
                        data1.put("field2", "Verified Profile");
                        data1.put("loc","Jamshedpur");
                        HashMap<String, String> data2 = new HashMap<>();
                        data2.put("userId", "Himan Shandil");
                        data2.put("domain", "A+");
                        data2.put("field1", "Available");
                        data2.put("field2", "Verified Profile");
                        data2.put("loc","Aali");
                        HashMap<String, String> data3 = new HashMap<>();
                        data3.put("userId", "Vibhash");
                        data3.put("domain", "A+");
                        data3.put("field1", "Available");
                        data3.put("field2", "Verified Profile");
                        data3.put("loc","Gopal nagar");
                        HashMap<String, String> data4 = new HashMap<>();
                        data4.put("userId", "Utkarsh");
                        data4.put("domain", "A+");
                        data4.put("field1", "Available");
                        data4.put("field2", "Verified Profile");
                        data4.put("loc","Red fort");
                        HashMap<String, String> data5 = new HashMap<>();
                        data5.put("userId", "Rakshul");
                        data5.put("domain", "A+");
                        data5.put("field1", "Available");
                        data5.put("field2", "Verified Profile");
                        data5.put("loc","Ganga Nagar");
                        dataList.add(data1);
                        dataList.add(data2);
                        dataList.add(data5);
                        dataList.add(data4);
                        dataList.add(data3);
                        Adapterclass1 adapter = new Adapterclass1(dataList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Blood.this));

                    }

                    if(state.getText().toString().equals("Punjab")&&city.getText().toString().equals("Amritsar")&&bloodgrp.getText().toString().equals("O-")){
                        List<HashMap<String, String>> dataList = new ArrayList<>();
                        HashMap<String, String> data4 = new HashMap<>();
                        data4.put("userId", "Yukta");
                        data4.put("domain", "O-");
                        data4.put("field1", "Available");
                        data4.put("field2", "Verified Profile");
                        data4.put("loc","Red fort");
                        HashMap<String, String> data5 = new HashMap<>();
                        data5.put("userId", "Kartik");
                        data5.put("domain", "O-");
                        data5.put("field1", "Available");
                        data5.put("field2", "Verified Profile");
                        data5.put("loc","Ganga Nagar");

                        dataList.add(data5);
                        dataList.add(data4);
                        Adapterclass1 adapter = new Adapterclass1(dataList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Blood.this));

                    }
                }
                if(!state.getText().toString().isEmpty()&&!city.getText().toString().isEmpty()&&!bloodgrp.getText().toString().isEmpty()){
                    if(state.getText().toString().equals("Delhi")&&city.getText().toString().equals("Aali")&&bloodgrp.getText().toString().equals("A+")){
                        List<HashMap<String, String>> dataList = new ArrayList<>();
                        HashMap<String, String> data1 = new HashMap<>();
                        data1.put("userId", "Bhomik jain");
                        data1.put("domain", "A+");
                        data1.put("field1", "Available");
                        data1.put("field2", "Verified Profile");
                        data1.put("loc","Jamshedpur");
                        HashMap<String, String> data2 = new HashMap<>();
                        data2.put("userId", "Sahil Shandil");
                        data2.put("domain", "A+");
                        data2.put("field1", "Available");
                        data2.put("field2", "Verified Profile");
                        data2.put("loc","Aali");
                        HashMap<String, String> data3 = new HashMap<>();
                        data3.put("userId", "Vibhash");
                        data3.put("domain", "A+");
                        data3.put("field1", "Available");
                        data3.put("field2", "Verified Profile");
                        data3.put("loc","Gopal nagar");
                        HashMap<String, String> data4 = new HashMap<>();
                        data4.put("userId", "Utkarsh");
                        data4.put("domain", "A+");
                        data4.put("field1", "Available");
                        data4.put("field2", "Verified Profile");
                        data4.put("loc","Red fort");
                        HashMap<String, String> data5 = new HashMap<>();
                        data5.put("userId", "Rakshul");
                        data5.put("domain", "A+");
                        data5.put("field1", "Available");
                        data5.put("field2", "Verified Profile");
                        data5.put("loc","Ganga Nagar");
                        dataList.add(data1);
                        dataList.add(data2);
                        dataList.add(data5);
                        dataList.add(data4);
                        dataList.add(data3);
                        Adapterclass1 adapter = new Adapterclass1(dataList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Blood.this));

                    }

                    if(state.getText().toString().equals("Delhi")&&city.getText().toString().equals("Aali")&&bloodgrp.getText().toString().equals("O-")){
                        List<HashMap<String, String>> dataList = new ArrayList<>();
                        HashMap<String, String> data4 = new HashMap<>();
                        data4.put("userId", "Utkarsh");
                        data4.put("domain", "O-");
                        data4.put("field1", "Available");
                        data4.put("field2", "Verified Profile");
                        data4.put("loc","Red fort");
                        HashMap<String, String> data5 = new HashMap<>();
                        data5.put("userId", "Rakshul");
                        data5.put("domain", "O-");
                        data5.put("field1", "Available");
                        data5.put("field2", "Verified Profile");
                        data5.put("loc","Ganga Nagar");

                        dataList.add(data5);
                        dataList.add(data4);
                        Adapterclass1 adapter = new Adapterclass1(dataList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Blood.this));

                    }
                }
            }
        };
        handler.postDelayed(runnable,2000);

    }
});
//        List<HashMap<String, String>> dataList = new ArrayList<>();
//        HashMap<String, String> data1 = new HashMap<>();
//        data1.put("userId", "Bhomik jain");
//        data1.put("domain", "A+");
//        data1.put("field1", "Available");
//        data1.put("field2", "Verified Profile");
//        data1.put("loc","location");
//        dataList.add(data1);
//        Adapterclass1 adapter = new Adapterclass1(dataList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        state=findViewById(R.id.state);
        city=findViewById(R.id.city);
        bloodgrp=findViewById(R.id.Bloodgrp);
        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,sates);
        state.setAdapter(adapterItems);
        state.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item =adapterView.getItemAtPosition(i).toString();
                if(item=="Delhi"){
                    adapterItems=new ArrayAdapter<>(Blood.this,R.layout.list_item,DelhiCity);
                  city.setAdapter(adapterItems);
                }
                if(item=="Punjab"){
                    adapterItems=new ArrayAdapter<>(Blood.this,R.layout.list_item,Punjab);
                    city.setAdapter(adapterItems);
                }
                if(item=="Uttar Pradesh"){
                    adapterItems=new ArrayAdapter<>(Blood.this,R.layout.list_item,UP);
                    city.setAdapter(adapterItems);
                }

            }
        });

        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,Bloodgrp);
        bloodgrp.setAdapter(adapterItems);
        bloodgrp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item =adapterView.getItemAtPosition(i).toString();
            }
        });
    }

}