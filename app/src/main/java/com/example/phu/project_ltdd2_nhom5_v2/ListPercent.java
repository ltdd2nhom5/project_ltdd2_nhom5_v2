package com.example.phu.project_ltdd2_nhom5_v2;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.phu.project_ltdd2_nhom5_v2.adapter_.KeHoachChiTieu;
import com.example.phu.project_ltdd2_nhom5_v2.database.Database;
import com.example.phu.project_ltdd2_nhom5_v2.model.NhomChiTieu;

import java.util.ArrayList;

public class ListPercent extends AppCompatActivity {

    public KeHoachChiTieu adapter;
    public ArrayList<NhomChiTieu> NCT = new ArrayList<NhomChiTieu>();
    public Database DAO = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lap_ke_hoach_layout);

        ListView list = (ListView) findViewById(R.id.my_list_view);
        DAO.getNhomChiTieu(NCT);

//        ArrayList<NhomChiTieu> listNCT = new ArrayList<NhomChiTieu>();
//        for (int i = 0; i < NCT.size(); i++) {
//            NhomChiTieu nhomChiTieu = new NhomChiTieu();
//
//            nhomChiTieu.setId(NCT.get(i).getId());
//            nhomChiTieu.setName(NCT.get(i).getName());
//            nhomChiTieu.setPhan_tram(NCT.get(i).getPhan_tram());
//
//            listNCT.add(nhomChiTieu);
//        }

        adapter = new KeHoachChiTieu (ListPercent.this, R.layout.card_layout, NCT);
        list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_lap_ke_hoach, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }
}
