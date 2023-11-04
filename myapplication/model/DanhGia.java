package com.example.myapplication.model;

public class DanhGia {
    private int IDdanhgia;
    private String NoiDungDanhGia;
    private String TenTK;
    private String tenTin;

    public DanhGia(String tentaikhoan, String noiDungDanhGia) {
        TenTK = tentaikhoan;
        NoiDungDanhGia =noiDungDanhGia;

    }

    public DanhGia(int iDdanhgia, String tenTK, String noiDungDanhGia) {
        this.IDdanhgia = iDdanhgia;
        NoiDungDanhGia = noiDungDanhGia;
        TenTK = tenTK;

    }

    public DanhGia(int IDdanhgia, String noiDungDanhGia, String tenTK, String tenTin) {
        this.IDdanhgia = IDdanhgia;
        NoiDungDanhGia = noiDungDanhGia;
        TenTK = tenTK;
        this.tenTin = tenTin;
    }

    public void setIDdanhgia(int IDdanhgia) {
        this.IDdanhgia = IDdanhgia;
    }










    public String getNoiDungDanhGia() {
        return NoiDungDanhGia;
    }

    public void setNoiDungDanhGia(String noiDungDanhGia) {
        NoiDungDanhGia = noiDungDanhGia;
    }

    public int getIDdanhgia() {
        return IDdanhgia;
    }



    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String tenTK) {
        TenTK = tenTK;
    }

    public String getTenTin() {
        return tenTin == null ? "" : tenTin;
    }

    public void setTenTin(String tenTin) {
        this.tenTin = tenTin;
    }
}
