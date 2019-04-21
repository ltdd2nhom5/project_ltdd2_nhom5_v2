package com.example.phu.project_ltdd2_nhom5_v2;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.phu.project_ltdd2_nhom5_v2.database.Database;
import com.example.phu.project_ltdd2_nhom5_v2.model.Chi;
import com.example.phu.project_ltdd2_nhom5_v2.model.NhomChiTieu;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.ArrayList;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mehdi.sakout.fancybuttons.FancyButton;

public class AddTransactionActivity extends AppCompatActivity {
    TextView txtChonNgay;
    Spinner spinNhomChi;
    ImageView imgDate;
    DatePickerDialog datePickerDialog;
    Calendar c;
    EditText edtMoney, edtNote;
    FancyButton btnSave;
    float so_tien_cho_phep;
    ArrayList<NhomChiTieu> nhomChiTieu_list;
    BoomMenuButton bmb;
    final int XEM_TIEN_CHI_TRONG_THANG_NAY = 0;
    final int MAN_HINH_THU = 1;
    final int DONG_MAN_HINH = 2;
    final int RESET = 3;
    final int MAN_HINH_LAP_KE_HOACH = 4;
    final int MAN_HINH_THONG_KE = 5;

    ArrayList<Integer> id_list = new ArrayList<>();
    ArrayList<String> tenNhomChiTieu_list = new ArrayList<>();

    int nhomChiTieuId_selected;
    String tenNhomChiTieu_selected;
    NhomChiTieu nhomChiTieu_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        spinNhomChi = (Spinner) findViewById(R.id.spinNhomChi);
        txtChonNgay = (TextView) findViewById(R.id.txtChonNgay);
        imgDate = (ImageView) findViewById(R.id.imvDate);
        // setup spinner
        spinNhomChi = (Spinner)findViewById(R.id.spinNhomChi);
        Database db = new Database(this);
        nhomChiTieu_list = new ArrayList<>();
        db.getNhomChiTieu(nhomChiTieu_list);
        for (NhomChiTieu nct:nhomChiTieu_list){
            id_list.add(nct.getId());
            tenNhomChiTieu_list.add(nct.getName());
        }
        String names[] = {"A","B","C"};
        Log.d("__test","kich thuoc: "+nhomChiTieu_list.size());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.spinner_nhomchitieu_item,tenNhomChiTieu_list);
        spinNhomChi.setAdapter(adapter);
        spinNhomChi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nhomChiTieuId_selected = id_list.get(position);
                tenNhomChiTieu_selected = tenNhomChiTieu_list.get(position);
                nhomChiTieu_selected = nhomChiTieu_list.get(position);
                Toast.makeText(AddTransactionActivity.this, "id:"+nhomChiTieuId_selected + " - ten: " + tenNhomChiTieu_selected , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//
        txtChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(AddTransactionActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtChonNgay.setText(year + "-" + (month+1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        edtMoney = (EditText) findViewById(R.id.edtMoney);
        edtNote = (EditText) findViewById(R.id.edtNote);

        btnSave = (FancyButton) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money_str = edtMoney.getText() + "";
                final String date_str = txtChonNgay.getText() + "";
                String nhom_chi = spinNhomChi.getSelectedItem().toString();
                final String note = edtNote.getText()+"";
                if (money_str.equals("")) {
                    YoYo.with(Techniques.Tada)
                            .duration(500)
                            .repeat(1)
                            .playOn(findViewById(R.id.edtMoney));

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            new SweetAlertDialog(AddTransactionActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Cảnh báo")
                                    .setContentText("Bạn chưa nhập số tiền")
                                    .show();
                        }
                    }, 1000);
                } else if (date_str.equals("")) {
                    YoYo.with(Techniques.Tada)
                            .duration(500)
                            .repeat(1)
                            .playOn(findViewById(R.id.txtChonNgay));

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            new SweetAlertDialog(AddTransactionActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Cảnh báo")
                                    .setContentText("Bạn chưa chọn ngày cho giao dịch")
                                    .show();
                        }
                    }, 1000);
                }
                else if (nhom_chi.equals("")) {
                    YoYo.with(Techniques.Tada)
                            .duration(500)
                            .repeat(1)
                            .playOn(findViewById(R.id.spinNhomChi));

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            new SweetAlertDialog(AddTransactionActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Cảnh báo")
                                    .setContentText("Bạn chưa chọn nhóm chi tiêu")
                                    .show();
                        }
                    }, 1000);
                }
                else {
                    final float money = Float.parseFloat(money_str);
                     so_tien_cho_phep = 50000;
                    so_tien_cho_phep = nhomChiTieu_selected.getTien_tieu_con_lai();
                    if (money > so_tien_cho_phep) {
                        new SweetAlertDialog(v.getContext(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Cảnh báo")
                                .setContentText("Bạn đã chi quá số tiền trong kế hoạch. " + "Bạn chỉ còn " + so_tien_cho_phep + " cho nhóm chi tiêu này !!!")

                                .show();
                    } else {
                        new SweetAlertDialog(AddTransactionActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Xác nhận")
                                .setContentText("Hãy xác nhận muốn thêm giao dịch?")
                                .setConfirmButton("Xác nhận", new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.cancel();
                                        Chi chi = new Chi();
                                        chi.setSo_tien_chi(money);
                                        chi.setGhi_chu(note);
                                        chi.setNgay_chi_tieu(date_str);
                                        chi.setNhom_chi_tieu(nhomChiTieuId_selected+"");
                                        Database db = new Database(AddTransactionActivity.this);
                                        db.insertChi(chi);
                                        so_tien_cho_phep -= money;
                                        nhomChiTieu_selected.setTien_tieu_con_lai(so_tien_cho_phep);
                                        db.update_nhom_chi_tieu(nhomChiTieu_selected);
                                        new SweetAlertDialog(AddTransactionActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                                .setTitleText("Thông báo")
                                                .setContentText("Bạn đã thêm 1 giao dịch")
                                                .show();

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
                }

            }
        });


        bmb = (BoomMenuButton) findViewById(R.id.bmb);
//        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++) {
//            bmb.addBuilder(new SimpleCircleButton.Builder()
//                    .normalImageRes(R.drawable.calenda));
//        }
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            int icon = 0;

            switch (i) {
                case DONG_MAN_HINH:
                    icon = R.drawable.close;
                    break;
                case MAN_HINH_LAP_KE_HOACH:
                    icon = R.drawable.plan;
                    break;
                case MAN_HINH_THU:
                    icon = R.drawable.money_in;
                    break;
                case RESET:
                    icon = R.drawable.refresh2;
                    break;
                case XEM_TIEN_CHI_TRONG_THANG_NAY:
                    icon = R.drawable.money_out;
                    break;
                case MAN_HINH_THONG_KE:
                    icon = R.drawable.chart;
                    break;

                default:
                    icon = R.drawable.money_icon;
                    break;
            }
            SimpleCircleButton.Builder builder = new SimpleCircleButton.Builder()
                    .normalImageRes(icon)
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            switch (index) {
                                case DONG_MAN_HINH:
                                    finish();
                                    break;
                                case RESET:
                                    txtChonNgay.setText("");
                                    edtMoney.setText("");
                                    edtNote.setText("");
                                    Toast.makeText(AddTransactionActivity.this, "save", Toast.LENGTH_SHORT).show();
                                    break;
                                case MAN_HINH_LAP_KE_HOACH:
                                    Intent intent = new Intent(AddTransactionActivity.this,CustomListPercent.class);
                                    startActivity(intent);
                                    break;
                                case MAN_HINH_THONG_KE:
                                    Intent intent2 = new Intent(AddTransactionActivity.this,SpendStatisticsActivity.class);
                                    startActivity(intent2);
                                    break;
                                case MAN_HINH_THU:
                                    Intent intent3 = new Intent(AddTransactionActivity.this,ThemKhoanThuActivity.class);
                                    startActivity(intent3);
                                    break;
                                case XEM_TIEN_CHI_TRONG_THANG_NAY:
                                    Toast.makeText(AddTransactionActivity.this, "Xem tong chi thang nay", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            Toast.makeText(AddTransactionActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                        }
                    });
            bmb.addBuilder(builder);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_them_giao_dich, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSaveChi:

//                db.insert_chi(chi);
//                Toast.makeText(this, "da them", Toast.LENGTH_SHORT).show();
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }


}
