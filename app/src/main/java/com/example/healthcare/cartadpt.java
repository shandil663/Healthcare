package com.example.healthcare;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cartadpt extends FirebaseRecyclerAdapter<Modal, cartadpt.myViewHolder>{
Context context;
    FirebaseAuth oth;
    DatabaseReference ref;
    FirebaseDatabase db;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public cartadpt(@NonNull FirebaseRecyclerOptions<Modal> options, Context context) {

        super(options);
        this.context=context;

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Modal model) {
        final Modal temp=getItem(position);

                holder.txt1.setText(model.getMedname());
                holder.txt2.setText(model.getMedprice());
                holder.txt3.setText(model.getQT());
        Glide.with(holder.img.getContext()).load(model.getMedimage()).placeholder(R.drawable.img_36).error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark).into(holder.img);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cartview,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;

        TextView txt1,txt2,txt3;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img=(ImageView) itemView.findViewById(R.id.imgmedview1);
            txt1=(TextView) itemView.findViewById(R.id.medname1);
            txt2=(TextView) itemView.findViewById(R.id.price1);
            txt3=(TextView) itemView.findViewById(R.id.qt1);

        }
    }
}
