package com.example.phuvo.recyclerview_v3;

import java.util.ArrayList;

public class MyDB {
    public static ArrayList<Product> product_list = new ArrayList<>();
    public static ArrayList<Integer> cart_items = new ArrayList<>();

    public MyDB() {
        this.product_list.add(new Product("iPhone 8", 1000, "nổi bật với điểm nhấn mặt lưng", R.drawable.ip8));
//        this.product_list.add(new Product("iPhone X", 700, "đã làm nên chuẩn mực, thương hiệu với sự thời thượng và hiện đại", R.drawable.ipx));
        this.product_list.add(new Product("Galaxy S9", 333, "Điểm thiết kế này mang lại độ bóng bẩy", R.drawable.s9));
        this.product_list.add(new Product("Galaxy S8", 470, "phủ kính cường lực thay vì nguyên", R.drawable.s8));
        this.product_list.add(new Product("Galaxy Note 8", 60, "thương hiệu với sự thời thượng", R.drawable.note8));
        this.product_list.add(new Product("Oppo F9", 1111, "hiện đại của mặt lưng phủ", R.drawable.oppof9));
        this.product_list.add(new Product("Oppo Find X", 2000, "đẹp mắt hơn cho sản phẩm", R.drawable.findx));
    }

    public static float getTotal() {
        float total = 0;
        for (int i = 0; i < product_list.size(); i++) {
            for (int item:cart_items){
                if (item == i){
                    total+= product_list.get(i).getPrice();
                }
            }

        }
        return total;
    }
}
