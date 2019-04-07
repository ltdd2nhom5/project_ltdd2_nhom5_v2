package com.example.phu.project_ltdd2_nhom5_v2.model;

public class NhomChiTieu {
    private int id;
    private String name;
    private float phan_tram;
    private float tien_tieu_con_lai;

    public void setId(int id) {
        this.id = id;
    }

    public NhomChiTieu(int id, String name, float phan_tram, float tien_tieu_con_lai) {
        this.id = id;
        this.name = name;
        this.phan_tram = phan_tram;
        this.tien_tieu_con_lai = tien_tieu_con_lai;

    }

    public int getId() {
        return id;
    }

    public NhomChiTieu() {

    }

    public String getName() {
        return name;
    }

    public float getPhan_tram() {
        return phan_tram;
    }

    public float getTien_tieu_con_lai() {
        return tien_tieu_con_lai;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPhan_tram(float phan_tram) {
        this.phan_tram = phan_tram;
    }

    public void setTien_tieu_con_lai(float tien_tieu_con_lai) {
        this.tien_tieu_con_lai = tien_tieu_con_lai;
    }
}
