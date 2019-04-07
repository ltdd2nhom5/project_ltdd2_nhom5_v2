package com.example.phu.project_ltdd2_nhom5_v2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListPercent extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] name;
    private final Integer[] imageId;
    private final Float[] percent;

    public CustomListPercent(Activity context, String[] name, Integer[] imageId, Float[] percent) {
        super(context, R.layout.card_layout, name);
        this.context = context;
        this.name = name;
        this.imageId = imageId;
        this.percent = percent;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.card_layout, null, true);

        TextView txtName = (TextView) rowView.findViewById(R.id.txtName);
        EditText edtPercent = (EditText) rowView.findViewById(R.id.edtPercent);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgView);
        txtName.setText(name[position]);
        edtPercent.setText(percent[position] + "");
        imageView.setImageResource(imageId[position]);

        return rowView;
    }
}
