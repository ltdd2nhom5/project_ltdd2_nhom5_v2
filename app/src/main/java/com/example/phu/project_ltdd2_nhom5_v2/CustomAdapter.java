package com.example.phu.project_ltdd2_nhom5_v2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtPercent;
        ImageView imgIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtName = (TextView) itemView.findViewById(R.id.txtName);
            this.txtPercent = (TextView) itemView.findViewById(R.id.txtPercent);
            this.imgIcon = (ImageView) itemView.findViewById(R.id.imgView);
        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);

        view.setOnClickListener(ListPercent.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView txtName = holder.txtName;
        TextView txtPercent = holder.txtPercent;
        ImageView imgIcon = holder.imgIcon;

        txtName.setText(dataSet.get(listPosition).getName());
        txtPercent.setText(dataSet.get(listPosition).getPercent());
        imgIcon.setImageResource(dataSet.get(listPosition).getImg());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
