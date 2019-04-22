package com.example.phuvo.recyclerview_v3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomAdapterCart extends RecyclerView.Adapter<MyCustomAdapterCart.MyViewHolderCart> {
    ArrayList<Product> data;

    public MyCustomAdapterCart(ArrayList<Product> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolderCart onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_layout,viewGroup,false);

        MyCustomAdapterCart.MyViewHolderCart holder = new MyCustomAdapterCart.MyViewHolderCart(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderCart myViewHolderCart, int i) {
        TextView txtPrice = myViewHolderCart.txtPrice;
        TextView txtTotal = myViewHolderCart.txtTotal;
        TextView txtTitle = myViewHolderCart.txtTitle;
        int index = MyDB.cart_items.get(i);
        txtTitle.setText(MyDB.product_list.get(index).getName());
        txtPrice.setText("- Giá: "+MyDB.product_list.get(index).getPrice()+"$");


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolderCart extends RecyclerView.ViewHolder {

        TextView txtTitle,txtPrice,txtTotal;


        public MyViewHolderCart(@NonNull View view) {
            super(view);
            txtTitle = (TextView) view.findViewById(R.id.txtTitle_cart_item);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice_cart_item);
            txtTotal = (TextView) view.findViewById(R.id.txtTotal_cart_item);

        }
    }
}
