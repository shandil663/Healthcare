package com.example.healthcare;

public class ReportEntry {
    private String comments;
    private String date;
    private String doctor;
    private String referredTo;
    private String type;

    public ReportEntry() { }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getReferredTo() {
        return referredTo;
    }

    public void setReferredTo(String referredTo) {
        this.referredTo = referredTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

