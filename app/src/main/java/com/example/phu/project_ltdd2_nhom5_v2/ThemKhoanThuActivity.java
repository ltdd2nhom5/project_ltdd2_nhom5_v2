package com.example.phu.project_ltdd2_nhom5_v2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.phu.project_ltdd2_nhom5_v2.model.Thu;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.ArrayList;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mehdi.sakout.fancybuttons.FancyButton;

public class ThemKhoanThuActivity extends AppCompatActivity {
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
    final int MAN_HINH_CHI = 1;
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
        setContentView(R.layout.them_khoan_thu_layout);

        txtChonNgay = (TextView) findViewById(R.id.txtChonNgay);
        imgDate = (ImageView) findViewById(R.id.imvDate);
        // setup spinner

//
        txtChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(ThemKhoanThuActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtChonNgay.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
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
                final String note = edtNote.getText() + "";
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
                            new SweetAlertDialog(ThemKhoanThuActivity.this, SweetAlertDialog.ERROR_TYPE)
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
                            new SweetAlertDialog(ThemKhoanThuActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Cảnh báo")
                                    .setContentText("Bạn chưa chọn ngày cho giao dịch")
                                    .show();
                        }
                    }, 1000);
                } else {
                    final float money = Float.parseFloat(money_str);


                    new SweetAlertDialog(ThemKhoanThuActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Xác nhận")
                            .setContentText("Hãy xác nhận muốn thêm khoản thu này?")
                            .setConfirmButton("Xác nhận", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.cancel();
                                    Thu thu = new Thu();
                                    thu.setSo_tien_thu(money);
                                    thu.setGhi_chu(note);
                                    thu.setNgay_thu(date_str);
                                    Database db = new Database(ThemKhoanThuActivity.this);
                                    db.insertThu(thu);
                                    new SweetAlertDialog(ThemKhoanThuActivity.this, SweetAlertDialog.SUCCESS_TYPE)
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
                case MAN_HINH_CHI:
                    icon = R.drawable.money_out;
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
                                    Toast.makeText(ThemKhoanThuActivity.this, "save", Toast.LENGTH_SHORT).show();
                                    break;
                                case MAN_HINH_LAP_KE_HOACH:
                                    Intent intent = new Intent(ThemKhoanThuActivity.this, CustomListPercent.class);
                                    startActivity(intent);
                                    break;
                                case MAN_HINH_THONG_KE:
                                    Intent intent2 = new Intent(ThemKhoanThuActivity.this, SpendStatisticsActivity.class);
                                    startActivity(intent2);
                                    break;
                                case MAN_HINH_CHI:
                                    Intent intent3 = new Intent(ThemKhoanThuActivity.this, AddTransactionActivity.class);
                                    startActivity(intent3);
                                    break;
                                case XEM_TIEN_CHI_TRONG_THANG_NAY:
                                    Toast.makeText(ThemKhoanThuActivity.this, "Xem tong chi thang nay", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            Toast.makeText(ThemKhoanThuActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                        }
                    });
            bmb.addBuilder(builder);
        }

    }


}
