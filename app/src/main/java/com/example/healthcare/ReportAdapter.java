package com.example.healthcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {
    private List<ReportEntry> reportList;

    public ReportAdapter(List<ReportEntry> reportList) {
        this.reportList = reportList;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item_layout, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        ReportEntry reportEntry = reportList.get(position);
        holder.bind(reportEntry);
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    static class ReportViewHolder extends RecyclerView.ViewHolder {
        TextView commentsTextView, dateTextView, doctorTextView, referredToTextView, typeTextView;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            commentsTextView = itemView.findViewById(R.id.commentsTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            doctorTextView = itemView.findViewById(R.id.doctorTextView);
            referredToTextView = itemView.findViewById(R.id.referredToTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
        }

        public void bind(ReportEntry reportEntry) {
            commentsTextView.setText(reportEntry.getComments());
            dateTextView.setText(reportEntry.getDate());
            doctorTextView.setText(reportEntry.getDoctor());
            referredToTextView.setText(reportEntry.getReferredTo());
            typeTextView.setText(reportEntry.getType());
        }
    }
}
