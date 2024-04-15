package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class cart extends AppCompatActivity{
    RecyclerView cartview;
    TextView txt;

    EditText txt1,txt2;
    Button btn,btnplace;
    cartadpt adp;
    FirebaseAuth oth;
    DatabaseReference ref;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        oth=FirebaseAuth.getInstance();
        txt1=findViewById(R.id.namefororder);
        txt2=findViewById(R.id.addressfororder);
        txt=findViewById(R.id.billamt);
        btnplace=findViewById(R.id.placeorder);
        btnplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long date=System.currentTimeMillis();
                SimpleDateFormat dateFormat =new SimpleDateFormat("dd / MMMM / yyyy - HH:mm", Locale.getDefault());
                String dateStr = dateFormat.format(date);

                db=FirebaseDatabase.getInstance();
                ref=db.getReference("Billing").child(oth.getCurrentUser().getUid()).child("orderstatus");
                ref.setValue("Order Placed");
                ref=db.getReference("Billing").child(oth.getCurrentUser().getUid()).child("Date");
                ref.setValue(dateStr);
                ref=db.getReference("Billing").child(oth.getCurrentUser().getUid()).child("Name");
                ref.setValue(txt1.getText().toString());
                ref=db.getReference("Billing").child(oth.getCurrentUser().getUid()).child("Address");
                ref.setValue(txt2.getText().toString());
                Toast.makeText(cart.this, "Order successful", Toast.LENGTH_SHORT).show();


            }
        });
        btn=findViewById(R.id.removebtn);
        readata(oth.getCurrentUser().getUid());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        db=FirebaseDatabase.getInstance();
                        ref=db.getReference("Cartitems").child(oth.getCurrentUser().getUid());
                        ref.removeValue();
//                        ref=db.getReference("Billing").child(oth.getCurrentUser().getUid());
//                        ref.removeValue();
                        Intent intent=new Intent(getApplicationContext(),Buydrugs.class);
                        startActivity(intent);
                        finish();
            }
        });
oth=FirebaseAuth.getInstance();
        FirebaseRecyclerOptions<Modal> options =
                new FirebaseRecyclerOptions.Builder<Modal>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Cartitems").child(oth.getCurrentUser().getUid()), Modal.class)
                        .build();

        cartview=findViewById(R.id.cartview);
        cartview.setLayoutManager(new LinearLayoutManager(this));
        adp=new cartadpt(options,getApplicationContext());
        cartview.setAdapter(adp);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adp.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adp.stopListening();
    }


    private void readata(String uid) {

        ref= FirebaseDatabase.getInstance().getReference("Billing");
        ref.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()){
                    if(task.getResult().exists()){


                        DataSnapshot data= task.getResult();
                        String sum= String.valueOf(data.child("Totalbill").getValue());
                        txt.setText(sum);


                    }

                    else{
                        Toast.makeText(cart.this, "No user found", Toast.LENGTH_SHORT).show();
                    }
                }

                else{
                    Toast.makeText(cart.this, "No data available", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}