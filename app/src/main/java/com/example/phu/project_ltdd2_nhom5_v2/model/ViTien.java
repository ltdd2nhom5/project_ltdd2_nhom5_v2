package com.example.phu.project_ltdd2_nhom5_v2.model;

import java.util.Date;

public class ViTien {
    private float so_du;
    private float quy_du;
    private Date thang;

    public void setSo_du(float so_du) {
        this.so_du = so_du;
    }

    public void setQuy_du(float quy_du) {
        this.quy_du = quy_du;
    }

    public void setThang(Date thang) {
        this.thang = thang;
    }

    public float getSo_du() {

        return so_du;
    }

    public float getQuy_du() {
        return quy_du;
    }

    public Date getThang() {
        return thang;
    }

    public ViTien(float so_du, float quy_du, Date thang) {

        this.so_du = so_du;
        this.quy_du = quy_du;
        this.thang = thang;
    }
}
