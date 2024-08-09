package healers.data.solutions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import healers.data.solutions.R;

import java.util.ArrayList;

public class RecyclerViewAdpater extends RecyclerView.Adapter<RecyclerViewAdpater.ViewHolder> {

    private Context context;

    public RecyclerViewAdpater(Context context, ArrayList<Integer> integerArrayList) {
        this.context = context;
        this.integerArrayList = integerArrayList;

    }

    private final ArrayList<Integer> integerArrayList;


    public RecyclerViewAdpater(ArrayList<Integer> integerArrayList) {
        this.integerArrayList = integerArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleimages,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdpater.ViewHolder holder, int position) {
        Glide.with(context).load(integerArrayList.get(position)).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return integerArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ViewHolder(@NonNull View itemView) {


            super(itemView);
            image=itemView.findViewById(R.id.imageView11);

        }
    }
}
