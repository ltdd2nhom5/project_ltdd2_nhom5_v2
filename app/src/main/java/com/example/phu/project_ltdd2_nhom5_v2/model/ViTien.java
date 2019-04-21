package com.example.phu.project_ltdd2_nhom5_v2.model;

import java.util.Date;

public class ViTien {
    private int id;
    private float so_du;
    private String thang;

    public ViTien() {
    }

    public ViTien(int id, float so_du, String thang) {

        this.id = id;
        this.so_du = so_du;
        this.thang = thang;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSo_du(float so_du) {
        this.so_du = so_du;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public float getSo_du() {

        return so_du;
    }

    public String getThang() {
        return thang;
    }
}
