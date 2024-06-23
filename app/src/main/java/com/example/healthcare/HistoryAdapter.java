package com.example.healthcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<HistoryEntry> historyList;

    public HistoryAdapter(List<HistoryEntry> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item_layout, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryEntry historyEntry = historyList.get(position);
        holder.bind(historyEntry);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView eventTextView, pharmacyTextView, physicianTextView, prescriptionTextView, remediesTextView;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            eventTextView = itemView.findViewById(R.id.eventTextView);
            pharmacyTextView = itemView.findViewById(R.id.pharmacyTextView);
            physicianTextView = itemView.findViewById(R.id.physicianTextView);
            prescriptionTextView = itemView.findViewById(R.id.prescriptionTextView);
            remediesTextView = itemView.findViewById(R.id.remediesTextView);
        }

        public void bind(HistoryEntry historyEntry) {
            eventTextView.setText(historyEntry.getEvent());
            pharmacyTextView.setText(historyEntry.getPharmacy());
            physicianTextView.setText(historyEntry.getPhysician());
            prescriptionTextView.setText(historyEntry.getPrescription());
            remediesTextView.setText(historyEntry.getRemedies());
        }
    }
}
