package com.example.phu.project_ltdd2_nhom5_v2;

public class DataModel {
    String name, percent;
    int id_, img;

    public DataModel(String name, String percent, int id_, int img) {
        this.name = name;
        this.percent = percent;
        this.id_ = id_;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getPercent() {
        return percent;
    }

    public int getId() {
        return id_;
    }

    public int getImg() {
        return img;
    }
}
