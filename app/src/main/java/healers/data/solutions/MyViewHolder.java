package healers.data.solutions;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import healers.data.solutions.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView time;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.titlebtw);
        time=itemView.findViewById(R.id.timebtw);
    }
}
