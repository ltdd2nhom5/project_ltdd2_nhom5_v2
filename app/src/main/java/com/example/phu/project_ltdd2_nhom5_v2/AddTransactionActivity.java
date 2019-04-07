package com.example.phu.project_ltdd2_nhom5_v2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mehdi.sakout.fancybuttons.FancyButton;

public class AddTransactionActivity extends AppCompatActivity {
    TextView txtChonNgay;

    ImageView imgDate;
    DatePickerDialog datePickerDialog;
    Calendar c;
    EditText edtMoney, edtNote;
    FancyButton btnSave;
    Spinner spinNhomChi;
    BoomMenuButton bmb;
    final int XEM_TIEN_CHI_TRONG_THANG_NAY = 0;
    final int MAN_HINH_THU = 1;
    final int DONG_MAN_HINH = 2;
    final int RESET = 3;
    final int MAN_HINH_LAP_KE_HOACH = 4;
    final int MAN_HINH_THONG_KE = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        spinNhomChi = (Spinner) findViewById(R.id.spinNhomChi);
        txtChonNgay = (TextView) findViewById(R.id.txtChonNgay);
        imgDate = (ImageView) findViewById(R.id.imvDate);
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
                        txtChonNgay.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, day, month, year);
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
                String date_str = txtChonNgay.getText() + "";
                String nhom_chi = spinNhomChi.getSelectedItem().toString();

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
                    float money = Float.parseFloat(money_str);
                    if (money > 50000) {
                        new SweetAlertDialog(v.getContext(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Cảnh báo")
                                .setContentText("Bạn đã chi quá số tiền trong kế hoạch")

                                .show();
                    } else {
                        new SweetAlertDialog(AddTransactionActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Xác nhận")
                                .setContentText("Hãy xác nhận muốn thêm giao dịch?")
                                .setConfirmButton("Xác nhận", new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.cancel();
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
                            }
                            Toast.makeText(AddTransactionActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                        }
                    });
            bmb.addBuilder(builder);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_them_giao_dich, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSaveChi:
                Chi chi = new Chi();
                chi.setSo_tien_chi(Float.parseFloat(edtMoney.getText().toString()));
                chi.setGhi_chu(edtNote.getText().toString());
                Database db = new Database(this);
                db.insert_chi(chi);
                Toast.makeText(this, "da them", Toast.LENGTH_SHORT).show();
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }


}
