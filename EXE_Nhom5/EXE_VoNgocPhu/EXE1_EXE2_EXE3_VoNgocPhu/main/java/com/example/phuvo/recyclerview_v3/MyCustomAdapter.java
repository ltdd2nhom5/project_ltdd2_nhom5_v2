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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {
    private ArrayList<Product> product_list;
    Animation rotate, blink_price,blink_btn, move, bounce_btn,together_product_imv;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imvImage;
        TextView txtTitle,txtDescription,txtPrice;
        Button btnAddToCart;

        public MyViewHolder(@NonNull View view) {
            super(view);
            imvImage = (ImageView)view.findViewById(R.id.imvImage);
            txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            txtDescription = (TextView) view.findViewById(R.id.txtDescription);
            btnAddToCart = (Button) view.findViewById(R.id.btnAddToCart);

        }
    }
    public MyCustomAdapter(ArrayList<Product> product_list){
        this.product_list = product_list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_layout,viewGroup,false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        TextView txtTitle = myViewHolder.txtTitle;
        TextView txtDescription = myViewHolder.txtDescription;
        final TextView txtPrice = myViewHolder.txtPrice;
        final ImageView imvImage = myViewHolder.imvImage;
        final Button btnAddToCart = myViewHolder.btnAddToCart;
        imvImage.setImageResource(product_list.get(i).getImage());
        txtTitle.setText(product_list.get(i).getName());
        txtPrice.setText("- Giá: "+product_list.get(i).getPrice()+"$");
        txtDescription.setText("- Mô tả: "+product_list.get(i).getDescription());
        rotate = AnimationUtils.loadAnimation(txtPrice.getContext(),R.anim.rotate);
        blink_price = AnimationUtils.loadAnimation(txtPrice.getContext(),R.anim.blink_price);
        blink_btn = AnimationUtils.loadAnimation(txtPrice.getContext(),R.anim.blink_btn);
        bounce_btn = AnimationUtils.loadAnimation(txtPrice.getContext(),R.anim.bounce_btn);
        together_product_imv = AnimationUtils.loadAnimation(imvImage.getContext(),R.anim.together_product_imv);

        txtPrice.startAnimation(blink_price);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               imvImage.startAnimation(rotate);
                btnAddToCart.startAnimation(blink_btn);
                btnAddToCart.startAnimation(bounce_btn);
                imvImage.startAnimation(together_product_imv);
                MyDB.cart_items.add(i);
                Toast.makeText(v.getContext(), "Đã thêm " + product_list.get(i).getName() + " vào giỏ hàng", Toast.LENGTH_SHORT).show();

            }
        });
        txtTitle.setText(product_list.get(i).getName());



    }


    @Override
    public int getItemCount() {
        return product_list.size();
    }
}
