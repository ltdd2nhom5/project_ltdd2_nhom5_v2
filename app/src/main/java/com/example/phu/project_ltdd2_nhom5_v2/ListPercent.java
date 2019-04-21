package com.example.phu.project_ltdd2_nhom5_v2;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.phu.project_ltdd2_nhom5_v2.adapter_.KeHoachChiTieu;
import com.example.phu.project_ltdd2_nhom5_v2.database.Database;
import com.example.phu.project_ltdd2_nhom5_v2.model.NhomChiTieu;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ListPercent extends AppCompatActivity {

    ImageView img_NCT;
    TextView txtName_NCT;
    EditText edtPercent_NCT;
    ListView list;
    public KeHoachChiTieu adapter;
    public ArrayList<NhomChiTieu> NCT = new ArrayList<NhomChiTieu>();
    public Database DAO = new Database(this);

    ArrayList<Integer> id_list = new ArrayList<>();
    ArrayList<String> tenNhomChiTieu_list = new ArrayList<>();
    ArrayList<Float> list_phantram_NCT = new ArrayList<>();

    NhomChiTieu nhomChiTieu_selected;
    Float total = 0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lap_ke_hoach_layout);

        img_NCT = (ImageView) findViewById(R.id.img_NCT);
        txtName_NCT = (TextView) findViewById(R.id.txtName_NCT);
        edtPercent_NCT = (EditText) findViewById(R.id.edtPercent_NCT);
        list = (ListView) findViewById(R.id.my_list_view);
        DAO.getNhomChiTieu(NCT);
        for (NhomChiTieu nct:NCT){
            id_list.add(nct.getId());
            tenNhomChiTieu_list.add(nct.getName());
            list_phantram_NCT.add(nct.getPhan_tram());
            total += nct.getPhan_tram();
        }
        adapter = new KeHoachChiTieu (ListPercent.this, R.layout.card_layout, NCT);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txtName_NCT.setText(tenNhomChiTieu_list.get(position));
                edtPercent_NCT.setText(list_phantram_NCT.get(position) + "");

                if (id_list.get(position) == 1){
                    img_NCT.setImageResource(R.drawable.move);
                } else if(id_list.get(position) == 2){
                    img_NCT.setImageResource(R.drawable.glass);
                } else if (id_list.get(position) == 3){
                    img_NCT.setImageResource(R.drawable.shopping);
                } else {
                    img_NCT.setImageResource(R.drawable.game);
                }
                nhomChiTieu_selected = NCT.get(position);
                total -= list_phantram_NCT.get(position);
            }

        });

        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_lap_ke_hoach, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:

                final String edtPercent = edtPercent_NCT.getText() + "";
                if (edtPercent.equalsIgnoreCase("")) {
                    YoYo.with(Techniques.Tada)
                            .duration(500)
                            .repeat(1)
                            .playOn(findViewById(R.id.edtPercent_NCT));

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            new SweetAlertDialog(ListPercent.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Cảnh báo")
                                    .setContentText("Bạn chưa chọn nhóm chi tiêu cần sửa")
                                    .show();
                        }
                    }, 1000);
                }else {
                    new SweetAlertDialog(ListPercent.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Xác nhận")
                        .setContentText("Hãy xác nhận muốn thay đổi phần trăm chi tiêu?")
                        .setConfirmButton("Xác nhận", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();
                                Float percent = Float.parseFloat(edtPercent_NCT.getText() + "");
                                total += percent;
                                if (total > 100) {
                                    total -= percent;
                                    new SweetAlertDialog(ListPercent.this, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("Cảnh báo")
                                            .setContentText("Số phầm trăm vượt quá 100% !!!" + total)
                                            .show();
                                } else {

                                    nhomChiTieu_selected.setPhan_tram(percent);
                                    DAO.update_nhom_chi_tieu(nhomChiTieu_selected);
                                    adapter.notifyDataSetChanged();
                                    txtName_NCT.setText("Tên nhóm chi tiêu");
                                    edtPercent_NCT.setText("");
                                    img_NCT.setImageResource(R.drawable.ic_launcher);
                                    new SweetAlertDialog(ListPercent.this, SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText("Thông báo")
                                            .setContentText("Bạn đã cập nhật lại phần trăm chi tiêu" + total)
                                            .show();
                                }
                            }
                        })
                        .setCancelButton("Hủy", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.cancel();
                            }
                        })
                        .show();
                }
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
