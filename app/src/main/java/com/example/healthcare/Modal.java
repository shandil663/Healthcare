package com.example.healthcare;

public class Modal {

    String Medname, Medprice,Medimage,Meddisc,QT;

     Modal() {
    }

    public Modal(String medname, String medprice, String medimage,String meddisc) {
        Medname = medname;
        Medprice = medprice;
        Medimage = medimage;
        Meddisc=meddisc;
    }

    public Modal(String medname,String medprice,String medimage){
         Medname=medname;
         Medprice=medprice;
         Medimage=medimage;
    }

    public Modal(String qt){
         QT=qt;
    }

    public String getQT() {
        return QT;
    }

    public void setQT(String QT) {
        this.QT = QT;
    }

    public String getMeddisc() {
        return Meddisc;
    }

    public void setMeddisc(String meddisc) {
        this.Meddisc = meddisc;
    }

    public String getMedname() {
        return Medname;
    }

    public void setMedname(String medname) {
        Medname = medname;
    }

    public String getMedprice() {
        return Medprice;
    }

    public void setMedprice(String medprice) {
        Medprice = medprice;
    }

    public String getMedimage() {
        return Medimage;
    }

    public void setMedimage(String medimage) {
        Medimage = medimage;
    }
}
