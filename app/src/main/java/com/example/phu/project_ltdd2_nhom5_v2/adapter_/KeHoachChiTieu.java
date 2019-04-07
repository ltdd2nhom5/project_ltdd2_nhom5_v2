package com.example.phu.project_ltdd2_nhom5_v2.adapter_;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phu.project_ltdd2_nhom5_v2.ListPercent;
import com.example.phu.project_ltdd2_nhom5_v2.R;
import com.example.phu.project_ltdd2_nhom5_v2.model.NhomChiTieu;

import java.util.ArrayList;

public class KeHoachChiTieu extends ArrayAdapter<NhomChiTieu> {

    private Activity context;
    private int layoutID;
    private ArrayList<NhomChiTieu> listNCT;
    public KeHoachChiTieu(@NonNull Activity context, int layoutID, @NonNull ArrayList<NhomChiTieu> listNCT) {
        super(context, layoutID, listNCT);
        this.context = context;
        this.layoutID = layoutID;
        this.listNCT = listNCT;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        LayoutInflater inflater = context.getLayoutInflater();
        view = inflater.inflate(layoutID, parent, false);
        NhomChiTieu nct = listNCT.get(position);


        TextView txtName = (TextView) view.findViewById(R.id.txtName);
        EditText edtPercent = (EditText) view.findViewById(R.id.edtPercent);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgView);

        txtName.setText(nct.getName());
        edtPercent.setText(nct.getPhan_tram() + "");

        if(nct.getId() == 1){
            imageView.setBackground(context.getResources().getDrawable(R.drawable.move));
        }
        else if(nct.getId() == 2){
            imageView.setBackground(context.getResources().getDrawable(R.drawable.glass));
        }
        else if (nct.getId() == 3){
            imageView.setBackground(context.getResources().getDrawable(R.drawable.shopping));
        }
        else {
            imageView.setBackground(context.getResources().getDrawable(R.drawable.game));
        }

        return view;
    }

}
