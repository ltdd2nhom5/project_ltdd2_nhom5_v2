package com.example.phu.project_ltdd2_nhom5_v2.model;


public class Chi {
    private int id;
    private String nhom_chi_tieu;
    private String ghi_chu;
    private String ngay_chi_tieu;
    private float so_tien_chi;
    private int img_nhom_chi_tieu;

    public Chi(int id, String nhom_chi_tieu, int img_nhom_chi_tieu, String ghi_chu, String ngay_chi_tieu, float so_tien_chi) {
        this.id = id;
        this.nhom_chi_tieu = nhom_chi_tieu;
        this.img_nhom_chi_tieu = img_nhom_chi_tieu;
        this.ghi_chu = ghi_chu;
        this.ngay_chi_tieu = ngay_chi_tieu;
        this.so_tien_chi = so_tien_chi;
    }

    public Chi() {
    }

    public int getImg_nhom_chi_tieu() {
        return img_nhom_chi_tieu;
    }

    public void setImg_nhom_chi_tieu(int img_nhom_chi_tieu) {
        this.img_nhom_chi_tieu = img_nhom_chi_tieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNhom_chi_tieu(String nhom_chi_tieu) {
        this.nhom_chi_tieu = nhom_chi_tieu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public void setNgay_chi_tieu(String ngay_chi_tieu) {
        this.ngay_chi_tieu = ngay_chi_tieu;
    }

    public void setSo_tien_chi(float so_tien_chi) {
        this.so_tien_chi = so_tien_chi;
    }

    public String getNhom_chi_tieu() { return nhom_chi_tieu; }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public String getNgay_chi_tieu() {
        return ngay_chi_tieu;
    }

    public float getSo_tien_chi() {
        return so_tien_chi;
    }
}
