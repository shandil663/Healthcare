package com.example.healthcare;

public class HistoryEntry {
    private String event;
    private String pharmacy;
    private String physician;
    private String prescription;
    private String remedies;

    public HistoryEntry() { }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
    }

    public String getPhysician() {
        return physician;
    }

    public void setPhysician(String physician) {
        this.physician = physician;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getRemedies() {
        return remedies;
    }

    public void setRemedies(String remedies) {
        this.remedies = remedies;
    }
}
