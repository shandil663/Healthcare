package com.example.healthcare;
import android.content.Context;
import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class adpt extends FirebaseRecyclerAdapter<Modal,adpt.myViewHolder>{
Context context;
    Float Totalbill=0f;

    String sum;
    FirebaseAuth oth;
    DatabaseReference ref;
    FirebaseDatabase db;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adpt(@NonNull FirebaseRecyclerOptions<Modal> options, Context context) {

        super(options);
        this.context=context;

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Modal model) {
        final Modal temp=getItem(position);


                holder.txt1.setText(model.getMedname());
                holder.txt2.setText(model.getMedprice());
                holder.txt3.setText(model.getMeddisc());
        Glide.with(holder.img.getContext()).load(model.getMedimage()).placeholder(R.drawable.img_36).error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark).into(holder.img);
        oth=FirebaseAuth.getInstance();
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              if(holder.txt4.getText().toString().isEmpty()){
                  Toast.makeText(context, "Enter Quantity Please", Toast.LENGTH_LONG).show();
              }
              else{
                  String a=holder.txt2.getText().toString();
                  String b=holder.txt4.getText().toString();
                  float x= Float.parseFloat(a);
                  float y=Float.parseFloat(b);
                  Float z= x*y;
                  String total=String.valueOf(z);
                  String uniqueID = UniqueIDGenerator.generateUniqueID();
                  Modal md=new Modal(model.getMedname(), model.getMedprice(), model.getMedimage());
                  db=FirebaseDatabase.getInstance();
                  ref=db.getReference("Cartitems").child(oth.getCurrentUser().getUid()).child(uniqueID);
                  ref.setValue(md).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                          Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();

                      }
                  });

                  db=FirebaseDatabase.getInstance();
                  ref=db.getReference("Billing").child(oth.getCurrentUser().getUid()).child(uniqueID);
                  ref.setValue(md).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                      }
                  });
                  ref=db.getReference("Cartitems").child(oth.getCurrentUser().getUid()).child(uniqueID).child("QT");
                  ref.setValue(b);
                  ref=db.getReference("Billing").child(oth.getCurrentUser().getUid()).child(uniqueID).child("QT");
                  ref.setValue(b);
                  ref=db.getReference("Billing").child(oth.getCurrentUser().getUid()).child("orderstatus");
                  ref.setValue("Order  not Placed");
                  ref=db.getReference("Cartitems").child(oth.getCurrentUser().getUid()).child(uniqueID).child("Total");
                  ref.setValue(total);

//                  ref=db.getReference("Billing").child(oth.getCurrentUser().getUid());
                  ref=db.getReference("Billing");
                  ref.addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          if(snapshot.hasChild("Totalbill")){

String k=readata(oth.getCurrentUser().getUid());
Totalbill= Float.parseFloat(k);
Totalbill+=z;
                  ref=db.getReference("Billing").child(oth.getCurrentUser().getUid()).child("Totalbill");
                  ref.setValue(Totalbill);


                          }

                          else{
                              Totalbill += z;
                              ref=db.getReference("Billing").child(oth.getCurrentUser().getUid()).child("Totalbill");
                              ref.setValue(Totalbill);

                          }
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });
              }
            }
        });



    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.buymedview,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        Button btn;
        TextView txt1,txt2,txt3;
        EditText txt4;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            btn=itemView.findViewById(R.id.addtocart);
            img=(ImageView) itemView.findViewById(R.id.imgmedview);
            txt1=(TextView) itemView.findViewById(R.id.medname);
            txt2=(TextView) itemView.findViewById(R.id.price);
            txt3=(TextView) itemView.findViewById(R.id.desc);
            txt4=(EditText) itemView.findViewById(R.id.getqt);
        }
    }
    private String readata(String uid) {

        ref= FirebaseDatabase.getInstance().getReference("Billing").child(uid);
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot data= task.getResult();
                         sum= String.valueOf(data.child("Totalbill").getValue());
                    }

                    else{
                        Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show();

                    }
                }

                else{
                    Toast.makeText(context, "some error", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return sum;
    }



}
