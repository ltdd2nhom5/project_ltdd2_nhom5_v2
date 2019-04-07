package com.example.phu.project_ltdd2_nhom5_v2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.Time;

import com.example.phu.project_ltdd2_nhom5_v2.adapter_.ThongKeChieuTieuAdapter;
import com.example.phu.project_ltdd2_nhom5_v2.database.Database;
import com.example.phu.project_ltdd2_nhom5_v2.model.Chi;
import com.example.phu.project_ltdd2_nhom5_v2.model.NhomChiTieu;
import com.example.phu.project_ltdd2_nhom5_v2.model.Thu;
import com.example.phu.project_ltdd2_nhom5_v2.model.ViTien;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SpendStatisticsActivity extends AppCompatActivity {
    Button btnViewTKCT;
    RelativeLayout viewStatistic;
    Intent intent;
    ListView list;
    ImageButton img;
    Calendar c;
    DatePickerDialog datePickerDialog;
    TextView txtDateChoose, nullTCKT, txtOutMoney, txtInput, txtMoneyFirst, txtMoneyOfMonth;

    public ArrayList<Chi> listCT = new ArrayList<Chi>();
    public ArrayList<NhomChiTieu> listNCT = new ArrayList<NhomChiTieu>();
    public ArrayList<Thu> listKT = new ArrayList<Thu>();
    public ArrayList<ViTien> listVT = new ArrayList<ViTien>();
    public ThongKeChieuTieuAdapter adapter;
    public Database DAO = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenditure_statistics);

        viewStatistic = (RelativeLayout) findViewById(R.id.viewStatis);
        btnViewTKCT = (Button) findViewById(R.id.viewTKCT);
        img = (ImageButton) findViewById(R.id.dateChoose);
        txtDateChoose = (TextView) findViewById(R.id.txtDateChoose);
        list = (ListView) findViewById(R.id.listItem);
        nullTCKT = (TextView) findViewById(R.id.nullTKCT);
        txtOutMoney = (TextView) findViewById(R.id.txtOutMoney);
        txtInput = (TextView) findViewById(R.id.txtIntMoney1);
        txtMoneyFirst = (TextView) findViewById(R.id.txtIntMoney);
        txtMoneyOfMonth = (TextView) findViewById(R.id.txtTotalMoney);

        Time time = new Time();
        time.setToNow();
        final int day_ = time.monthDay;
        final int month_ = time.month;
        final int year_ = time.year;
        String date = year_ + "/" + month_ + "/" + day_;
        txtDateChoose.setText(date);

        DAO.getChiTieu(listCT);
        DAO.getNhomChiTieu(listNCT);
        DAO.getThu(listKT);
        DAO.getVi(listVT);


        viewStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SpendStatisticsActivity.this, ThemKhoanThuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date_ = new Date();
                int day = date_.getDay();
                int month = date_.getMonth();
                int year = date_.getYear();
                datePickerDialog = new DatePickerDialog(SpendStatisticsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDateChoose.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        btnViewTKCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtDateChoose.getText().length() == 0)
                {
                    nullTCKT.setTextColor(getResources().getColor(R.color.red));
                    nullTCKT.setText("Vui lòng chọn ngày trước khi xem!");
                    txtOutMoney.setText("0 đ");
                    txtInput.setText("0 đ");
                    Toast.makeText(SpendStatisticsActivity.this,
                            "Vui lòng chọn ngày trước khi xem!"
                            , Toast.LENGTH_SHORT).show();
                }
                else {
                    nullTCKT.setText("");
                    String[] arrDate = txtDateChoose.getText().toString().split("/");
                    String month = arrDate[1];
                    String year = arrDate[2];

                    ArrayList<Chi> listTKCT = new ArrayList<Chi>();

                    for (int i = 0; i < listCT.size(); i++) {
                        String[] arrDateCT = listCT.get(i).getNgay_chi_tieu().split("-");
                        if (month.equalsIgnoreCase(arrDateCT[1]) && year.equalsIgnoreCase(arrDateCT[0]))
                        {
                            for(int j = 0; j < listNCT.size(); j++)
                            {
                                if(listNCT.get(j).getId() == Integer.parseInt(listCT.get(i).getNhom_chi_tieu()))
                                {
                                    Chi chi = new Chi();
                                    chi.setId(listCT.get(i).getId());
                                    chi.setNhom_chi_tieu(listNCT.get(j).getName());
                                    chi.setImg_nhom_chi_tieu(listCT.get(i).getImg_nhom_chi_tieu());
                                    chi.setSo_tien_chi(listCT.get(i).getSo_tien_chi());
                                    chi.setGhi_chu(listCT.get(i).getGhi_chu());
                                    chi.setNgay_chi_tieu(listCT.get(i).getNgay_chi_tieu());
                                    listTKCT.add(chi);
                                }
                            }
                        }
                    }
                    float fTotalThu = 0.0f;
                    int nDemThu = 0;
                    for (int i = 0; i < listKT.size(); i++){
                        String[] arrDateCT = listKT.get(i).getNgay_thu().split("-");
                        if (month.equalsIgnoreCase(arrDateCT[1]) && year.equalsIgnoreCase(arrDateCT[0])) {
                            fTotalThu += listKT.get(i).getSo_tien_thu();
                            nDemThu++;
                        }
                    }
                    if(nDemThu == 0) {
                        txtInput.setText("0 đ");
                    }
                    else {
                        txtInput.setText(fTotalThu + " đ");
                    }
                    float fSoDu = 0.0f;
                    int nDemSoDu = 0;
                    for (int i = 0; i < listVT.size(); i++)
                    {
                        String[] arrDateCT = listVT.get(i).getThang().split("-");
                        if(Integer.parseInt(arrDateCT[0]) <= Integer.parseInt(year))
                        {
                            if(Integer.parseInt(arrDateCT[1]) <= Integer.parseInt(month))
                            {
                                fSoDu += listVT.get(i).getSo_du();
                                nDemSoDu++;
                            }
                        }
                    }
                    if(nDemSoDu == 0)
                    {
                        txtMoneyFirst.setText("0 đ");
                    }else {
                        txtMoneyFirst.setText(fSoDu + " đ");
                    }
                    if(listTKCT.size() != 0)
                    {
                        float totalChi = 0.0f;
                        for(int i = 0; i < listTKCT.size(); i++)
                        {
                            totalChi += listTKCT.get(i).getSo_tien_chi();
                        }
                        txtOutMoney.setText("-" + totalChi + " đ");
                        nullTCKT.setText("");
                        txtMoneyOfMonth.setText((double)(fTotalThu + fSoDu - totalChi) + "");
                        adapter = new ThongKeChieuTieuAdapter (SpendStatisticsActivity.this, R.layout.list_item_layout, listTKCT);
                        list.setAdapter(adapter);
                    }
                    else {
                        txtOutMoney.setText("0 đ");
                        nullTCKT.setTextColor(getResources().getColor(R.color.red));
                        nullTCKT.setText("Không có tiền chi trong tháng " + month + "!");
                    }
                }
            }
        });
    }
}