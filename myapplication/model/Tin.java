package com.example.myapplication.model;

public class Tin {

    private int ID;
    private String TenTin;
    private String NoiDung;
    private String Anh;
    private int ID_TK;

    public Tin(String tenTin, String noiDung, String anh, int ID_TK) {
        TenTin = tenTin;
        NoiDung = noiDung;
        Anh = anh;
        this.ID_TK = ID_TK;
    }

    public Tin(int ID, String tenTin, String noiDung, String anh, int ID_TK) {
        this.ID = ID;
        TenTin = tenTin;
        NoiDung = noiDung;
        Anh = anh;
        this.ID_TK = ID_TK;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenTin() {
        return TenTin;
    }

    public void setTenTin(String tenTin) {
        TenTin = tenTin;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public int getID_TK() {
        return ID_TK;
    }

    public void setID_TK(int ID_TK) {
        this.ID_TK = ID_TK;
    }
}
