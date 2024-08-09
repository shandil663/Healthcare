package healers.data.solutions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import healers.data.solutions.R;

import java.util.HashMap;
import java.util.List;

public class Adapterclass1 extends RecyclerView.Adapter<Adapterclass1.ViewHolder> {

    private List<HashMap<String, String>> dataList; // Replace with your actual data structure if available

    public Adapterclass1(List<HashMap<String, String>> dataList) {
        this.dataList = dataList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blood_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashMap<String, String> data = dataList.get(position);

        holder.userIdTextView.setText(data.get("userId"));
        holder.domainTextView.setText(data.get("domain"));
        holder.field1TextView.setText(data.get("field1"));
        holder.field2TextView.setText(data.get("field2"));
        holder.loc.setText(data.get("loc"));
        holder.imageView.setImageResource(R.drawable.bloodimage);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView userIdTextView;
        TextView domainTextView;
        TextView field1TextView;
        TextView field2TextView;
        TextView loc;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            userIdTextView = itemView.findViewById(R.id.userid);
            domainTextView = itemView.findViewById(R.id.domain);
            field1TextView = itemView.findViewById(R.id.field1TextView);
            field2TextView = itemView.findViewById(R.id.field2TextView);
            loc=itemView.findViewById(R.id.loc);
            imageView = itemView.findViewById(R.id.imgid);
        }
    }
}
