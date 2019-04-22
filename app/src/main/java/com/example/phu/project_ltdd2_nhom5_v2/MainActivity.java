package com.example.phu.project_ltdd2_nhom5_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.phu.project_ltdd2_nhom5_v2.database.Database;
import com.example.phu.project_ltdd2_nhom5_v2.model.NhomChiTieu;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends AppCompatActivity {
    Button btnThemKhoanThu, btnThemKhoanChi, btnThongKe,btnLapKeHoach,btnThemDuLieu;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnThemKhoanChi = (Button)findViewById(R.id.btnThemKhoanChi);
        btnThemKhoanThu = (Button)findViewById(R.id.btnThemKhoanThu);
        btnThongKe = (Button)findViewById(R.id.btnThongKe);
        btnLapKeHoach = (Button)findViewById(R.id.btnLapKeHoach);
        btnThemDuLieu = (Button)findViewById(R.id.btnThemDuLieu);


        btnThemDuLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(MainActivity.this);
                db.insert_du_lieu_mau();
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Thông báo")
                        .setContentText("Dữ liệu mẫu đã được thêm.")
                        .show();
            }
        });
        btnThemKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,AddTransactionActivity.class);
                startActivity(intent);
            }
        });

        btnThemKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,ThemKhoanThuActivity.class);
                startActivity(intent);
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,SpendStatisticsActivity.class);
                startActivity(intent);
            }
        });
        btnLapKeHoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,ListPercent.class);
                startActivity(intent);
            }
        });

    }
}
