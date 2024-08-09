package healers.data.solutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import healers.data.solutions.R;
import healers.data.solutions.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class appointmentbooking extends AppCompatActivity {


    Button btn,selectdoc;

    CheckBox checkBox;

    LottieAnimationView lottieAnimationView1,lottieAnimationView2;
      CardView card,card1;


    EditText edit1,edit2,edit3;

    ActivityMainBinding binding;
    FirebaseDatabase db;

    FirebaseAuth oth;
    DatabaseReference ref,ref1;

    String HOSNAME,DOCNAME,ILL,TIME,NAME,AGE;


 String[] items={"Distt. Hospital, Amritsar", "SDH, Ajnala", "SDH, Baba Bakala", "CHC Lopoke", "CHC Manawala", "CHC Tarsikka", "CHC Majitha", "Community Health Centre, Bhadaur", "Community Health Centre, Dhanaula", "Community Health Centre, Tapa", "District Hospital, Barnala", "Women & Child Hospital, Bathinda", "Sub Divisional Hospital, Talwandi Sabo", "Community Health Centre, Sangat", "Community Health Centre, Nathana", "Community Health Centre, Maur Mandi", "Community Health Centre, Bhucho Mandi", "Community Health Centre, Mehraj", "Community Health Centre, Bhagat", "Sub Divisional Hospital Ram Pura Phul","Community Health Centre, Bhatinda", "Community Health Centre, Nathana", "Community Health Centre, Maur Mandi", "Community Health Centre, Bhucho Mandi", "Community Health Centre, Mehraj", "Community Health Centre, Bhagat", "Sub Divisional Hospital, Rampura Phul", "SDH, Bariwala", "SDH, Malout", "CHC, Lambi", "CHC, Gidderbaha", "SDH, Fazilka", "SDH, Abohar", "Civil Hospital, Muktsar", "SDH, Gurusar Sadhar", "SDH, Jagraon", "CHC, Raikot", "SDH, Moga", "SDH, Nihal Singh Wala", "CHC, Dharamkot",};
//    String[] items1={"Dr. Rajesh Sharma", "Dr. Priya Patel", "Dr. Alok Kumar", "Dr. Nandini Singh", "Dr. Vikram Desai", "Dr. Anjali Gupta", "Dr. Arjun Reddy", "Dr. Meera Khanna", "Dr. Sanjay Joshi", "Dr. Pooja Verma", "Dr. Ravi Menon", "Dr. Naina Kapoor", "Dr. Rohit Singhania", "Dr. Kavita Rastogi", "Dr. Anil Agarwal", "Dr. Ananya Das", "Dr. Sameer Malhotra", "Dr. Sneha Choudhury", "Dr. Suresh Iyer", "Dr. Nisha Sharma", "Dr. Prakash Kapoor", "Dr. Ayesha Pandey", "Dr. Arvind Rajput", "Dr. Deepika Rao", "Dr. Raghav Mehta", "Dr. Shalini Gupta", "Dr. Rahul Verma", "Dr. Kiran Nair", "Dr. Shashi Joshi", "Dr. Neelam Malhotra", "Dr. Aditya Chauhan", "Dr. Simran Ahuja", "Dr. Vikas Kapoor", "Dr. Diya Reddy", "Dr. Abhishek Patel", "Dr. Preeti Sharma", "Dr. Rajat Khanna", "Dr. Karishma Kumar", "Dr. Ajay Singh", "Dr. Anushka Bhatia",};
    String[] items2={"9:00am to 10:00am","10:00am to 11:00am","11:00am to 12:00pm","1:00pm to 2:00pm","2:00pm to 3:00pm"};

    String[] fever={"Dr. Rakshul","Dr. Bhomik","Dr. Ankita Sharma"};

    String [] body={"Dr. Anil Agarwal", "Dr. Ananya Das", "Dr. Sameer Malhotra", "Dr. Sneha"};
 AutoCompleteTextView autoCompleteTxt,autoCompleteTxt1,autoCompleteTxt2;
 ArrayAdapter<String> adapterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentbooking);
        selectdoc=findViewById(R.id.selectdoc);
        card1=findViewById(R.id.card1);
        ref1=FirebaseDatabase.getInstance().getReference();
//        fetchDoctorNames();
        checkBox=findViewById(R.id.checkBox);
        lottieAnimationView1=findViewById(R.id.animationView1);
        lottieAnimationView2=findViewById(R.id.applod);
        card=findViewById(R.id.cardbackfromappoint);
        btn=findViewById(R.id.appoint);
        autoCompleteTxt=findViewById(R.id.auto_complete_txt);
        autoCompleteTxt1=findViewById(R.id.auto_complete_txt1);
        autoCompleteTxt2=findViewById(R.id.auto_complete_txt3);
        edit1=findViewById(R.id.editTextText);
        edit2=findViewById(R.id.editTextText1);
        edit3=findViewById(R.id.editTextText3);
        oth=FirebaseAuth.getInstance();

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow(view);
                checkBox.setTextColor(Color.RED);
            }
        });

        selectdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDoctorNames();
                if(!edit1.getText().toString().isEmpty()){
                lottieAnimationView1.setVisibility(View.GONE);
                card1.setVisibility(View.GONE);
                lottieAnimationView2.setVisibility(View.VISIBLE);

                Handler handler=new Handler();
                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        lottieAnimationView1.setVisibility(View.VISIBLE);
                        card1.setVisibility(View.VISIBLE);
                        lottieAnimationView2.setVisibility(View.GONE);
                        if(edit1.getText().toString().equals("Fever")){
                            adapterItems=new ArrayAdapter<String>(appointmentbooking.this,R.layout.list_item,fever);
                            autoCompleteTxt1.setAdapter(adapterItems);
                            autoCompleteTxt1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    String item =adapterView.getItemAtPosition(i).toString();
                                }
                            });
                        }
                        if(edit1.getText().toString().equals("Body Pain")){
                            adapterItems=new ArrayAdapter<String>(appointmentbooking.this,R.layout.list_item,body);
                            autoCompleteTxt1.setAdapter(adapterItems);
                            autoCompleteTxt1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    String item =adapterView.getItemAtPosition(i).toString();
                                }
                            });
                        }

                        else{
                            fetchDoctorNames();
                        }
                    }
                };
                handler.postDelayed(runnable,2000);
            }}
        });
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
                    lottieAnimationView1.setVisibility(View.GONE);
                    card1.setVisibility(View.GONE);
                    lottieAnimationView2.setVisibility(View.VISIBLE);
                    Patients patients=new Patients(HOSNAME,DOCNAME,ILL,TIME,NAME,AGE);
                    db=FirebaseDatabase.getInstance();
                    ref=db.getReference("Patients").child(oth.getCurrentUser().getUid());
                    ref.setValue(patients).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            lottieAnimationView2.setVisibility(View.GONE);
                            Toast.makeText(appointmentbooking.this, "Appointment booked", Toast.LENGTH_SHORT).show();

                            Handler handler=new Handler();
                            Runnable runnable=new Runnable() {
                                @Override
                                public void run() {
                                    Intent booked=new Intent(getApplicationContext(), bookedappointement.class);
                                    startActivity(booked);
                                    finish();
                                }
                            };
                            handler.postDelayed(runnable,2000);

                        }
                    });

//                    Toast.makeText(appointmentbooking.this, "Appointment booked", Toast.LENGTH_SHORT).show();

//                    booked.putExtra("hosname",HOSNAME);
//                    booked.putExtra("docname",DOCNAME);
//                    booked.putExtra("ill",ILL);
//                    booked.putExtra("name",NAME);
//                    booked.putExtra("age",AGE);
//                    booked.putExtra("time",TIME);
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
//        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,items1);
//        autoCompleteTxt1.setAdapter(adapterItems);
//        autoCompleteTxt1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String item =adapterView.getItemAtPosition(i).toString();
//            }
//        });
        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,items2);
        autoCompleteTxt2.setAdapter(adapterItems);
        autoCompleteTxt2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item =adapterView.getItemAtPosition(i).toString();
            }
        });

    }
    private void showPopupWindow(View anchorView) {
        View rootView = findViewById(android.R.id.content); // Get activity's top view
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window_layout, null);
        PopupWindow popupWindow = new PopupWindow(popupView,1000, 800, true);
        popupWindow.showAtLocation(anchorView,Gravity.CENTER,0,0);

        EditText editText1 = popupView.findViewById(R.id.editText1);
        EditText editText2 = popupView.findViewById(R.id.editText2);
        Button submitButton = popupView.findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editText1.getText().toString();
                String text2 = editText2.getText().toString();

                // Do something with the entered data (text1 and text2)
                popupWindow.dismiss();
            }
        });
    }


    private void fetchDoctorNames() {
        ref1.child("hospital_12345").child("doctors").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> doctorNames = new ArrayList<>();

                for (DataSnapshot doctorSnapshot : dataSnapshot.getChildren()) {
                    String doctorName = doctorSnapshot.child("doctor_name").getValue(String.class);
                    if (doctorName != null) {
                        doctorNames.add(doctorName);
                    }
                }

           String []  fromfriebasedoctore = doctorNames.toArray(new String[0]);

                for (String name : fromfriebasedoctore) {
                    Log.d("Doctor Name", name);
                }
                for (String name : fromfriebasedoctore) {
                    System.out.println(name);
                }
               populateAutoCompleteTextView(fromfriebasedoctore);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Fetch Error", "loadPost:onCancelled", databaseError.toException());
            }
        });}

    private void populateAutoCompleteTextView(String[] doctorNamesArray) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, doctorNamesArray);
        autoCompleteTxt1.setAdapter(adapter);
    }

}