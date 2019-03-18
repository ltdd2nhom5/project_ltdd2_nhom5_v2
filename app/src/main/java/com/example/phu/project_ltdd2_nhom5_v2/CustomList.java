package com.example.phu.project_ltdd2_nhom5_v2;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] name;
    private final Integer[] imageId;
    private final String[] money;
    public CustomList(Activity context,
                      String[] name, Integer[] imageId, String[] money) {
        super(context, R.layout.list_item_layout, name);
        this.context = context;
        this.name = name;
        this.imageId = imageId;
        this.money = money;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item_layout, null, true);
        TextView txtName = (TextView) rowView.findViewById(R.id.lblName);
        TextView txtMoney = (TextView) rowView.findViewById(R.id.money);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        txtName.setText(name[position]);
        txtMoney.setText(money[position]);
        imageView.setImageResource(imageId[position]);

        return rowView;
    }
}
