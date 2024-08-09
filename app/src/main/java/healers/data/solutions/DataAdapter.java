package healers.data.solutions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import healers.data.solutions.R;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private List<Patient> patientList;

    public DataAdapter(List<Patient> patientList) {
        this.patientList = patientList;
    }
    public void updateData(List<Patient> newPatientList) {
        this.patientList = newPatientList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Patient patient = patientList.get(position);
        holder.bind(patient);
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, ageTextView, bloodGroupTextView, genderTextView, heightTextView, locationTextView, weightTextView;
        RecyclerView historyRecyclerView, reportsRecyclerView;
        TextView historyLabel, reportsLabel;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            ageTextView = itemView.findViewById(R.id.ageTextView);
            bloodGroupTextView = itemView.findViewById(R.id.bloodGroupTextView);
            genderTextView = itemView.findViewById(R.id.genderTextView);
            heightTextView = itemView.findViewById(R.id.heightTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            weightTextView = itemView.findViewById(R.id.weightTextView);
            historyRecyclerView = itemView.findViewById(R.id.historyRecyclerView);
            reportsRecyclerView = itemView.findViewById(R.id.reportsRecyclerView);
            historyLabel = itemView.findViewById(R.id.historyLabel);
            reportsLabel = itemView.findViewById(R.id.reportsLabel);
        }

        public void bind(Patient patient) {
            nameTextView.setText(patient.getName());
            ageTextView.setText(patient.getAge());
            bloodGroupTextView.setText(patient.getBloodGroup());
            genderTextView.setText(patient.getGender());
            heightTextView.setText(patient.getHeight());
            locationTextView.setText(patient.getLocation());
            weightTextView.setText(patient.getWeight());

            if (patient.getHistory() != null && !patient.getHistory().isEmpty()) {
                historyLabel.setVisibility(View.VISIBLE);
                historyRecyclerView.setVisibility(View.VISIBLE);
                HistoryAdapter historyAdapter = new HistoryAdapter(patient.getHistory());
                historyRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
                historyRecyclerView.setAdapter(historyAdapter);
            } else {
                historyLabel.setVisibility(View.GONE);
                historyRecyclerView.setVisibility(View.GONE);
            }

            if (patient.getReports() != null && !patient.getReports().isEmpty()) {
                reportsLabel.setVisibility(View.VISIBLE);
                reportsRecyclerView.setVisibility(View.VISIBLE);
                ReportAdapter reportAdapter = new ReportAdapter(patient.getReports());
                reportsRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
                reportsRecyclerView.setAdapter(reportAdapter);
            } else {
                reportsLabel.setVisibility(View.GONE);
                reportsRecyclerView.setVisibility(View.GONE);
            }
        }
    }
}
