package com.example.phu.project_ltdd2_nhom5_v2.model;

import java.util.Date;

public class Thu {

    public Thu(){

    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String ghi_chu;
    private Date ngay_thu;
    private float so_tien_thu;

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public void setNgay_thu(Date ngay_thu) {
        this.ngay_thu = ngay_thu;
    }

    public void setSo_tien_thu(float so_tien_thu) {
        this.so_tien_thu = so_tien_thu;
    }

    public String getGhi_chu() {

        return ghi_chu;
    }

    public Date getNgay_thu() {
        return ngay_thu;
    }

    public float getSo_tien_thu() {
        return so_tien_thu;
    }

    public Thu(String ghi_chu, Date ngay_thu, float so_tien_thu) {

        this.ghi_chu = ghi_chu;
        this.ngay_thu = ngay_thu;
        this.so_tien_thu = so_tien_thu;
    }
}
