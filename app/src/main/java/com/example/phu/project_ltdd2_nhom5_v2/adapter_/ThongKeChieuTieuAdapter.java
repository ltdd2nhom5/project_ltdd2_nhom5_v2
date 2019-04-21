package com.example.phu.project_ltdd2_nhom5_v2.adapter_;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phu.project_ltdd2_nhom5_v2.R;
import com.example.phu.project_ltdd2_nhom5_v2.database.Database;
import com.example.phu.project_ltdd2_nhom5_v2.model.Chi;
import com.example.phu.project_ltdd2_nhom5_v2.model.NhomChiTieu;

import java.util.ArrayList;

public class ThongKeChieuTieuAdapter extends ArrayAdapter<Chi> {
    private Activity context;
    private int layoutID;
    private ArrayList<Chi> listTKCT;
    public ThongKeChieuTieuAdapter(@NonNull Activity context, int layoutID, @NonNull ArrayList<Chi> listTKCT) {
        super(context, layoutID, listTKCT);
        this.context = context;
        this.layoutID = layoutID;
        this.listTKCT = listTKCT;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        LayoutInflater inflater = context.getLayoutInflater();
        view = inflater.inflate(layoutID, parent, false);
        Chi tkct = listTKCT.get(position);

        TextView txtName = (TextView) view.findViewById(R.id.lblName);
        TextView txtMoney = (TextView) view.findViewById(R.id.money);
        TextView txtNote = (TextView) view.findViewById(R.id.note);
        TextView txtNgayChi = (TextView) view.findViewById(R.id.month_year);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);

        txtName.setText(tkct.getNhom_chi_tieu());
        txtMoney.setText("-"+ tkct.getSo_tien_chi() + "");
        txtNote.setText(tkct.getGhi_chu());
        txtNgayChi.setText(tkct.getNgay_chi_tieu() + "");

        if(tkct.getImg_nhom_chi_tieu() == 1){
            imageView.setBackground(context.getResources().getDrawable(R.drawable.move));
        }
        else if(tkct.getImg_nhom_chi_tieu() == 2){
            imageView.setBackground(context.getResources().getDrawable(R.drawable.glass));
        }
        else if (tkct.getImg_nhom_chi_tieu() == 3){
            imageView.setBackground(context.getResources().getDrawable(R.drawable.shopping));
        }
        else {
            imageView.setBackground(context.getResources().getDrawable(R.drawable.game));
        }

        return view;
    }
}
