package com.example.phu.project_ltdd2_nhom5_v2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.Time;

import com.example.phu.project_ltdd2_nhom5_v2.adapter_.ThongKeChieuTieuAdapter;
import com.example.phu.project_ltdd2_nhom5_v2.database.Database;
import com.example.phu.project_ltdd2_nhom5_v2.model.Chi;
import com.example.phu.project_ltdd2_nhom5_v2.model.NhomChiTieu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SpendStatisticsActivity extends AppCompatActivity {
    Button btnViewTKCT;
    RelativeLayout viewStatistic;
    Intent intent;
    ListView list;
    ImageButton img;
    Calendar c;
    DatePickerDialog datePickerDialog;
    TextView txtDateChoose, nullTCKT, txtOutMoney;

    public ArrayList<Chi> listCT = new ArrayList<Chi>();
    public ArrayList<NhomChiTieu> listNCT = new ArrayList<NhomChiTieu>();
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


        Time time = new Time();
        time.setToNow();
        final int day_ = time.monthDay;
        final int month_ = time.month;
        final int year_ = time.year;
        String date = year_ + "/" + month_ + "/" + day_;
        txtDateChoose.setText(date);

        DAO.getChiTieu(listCT);
        DAO.getNhomChiTieu(listNCT);




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
                c = Calendar.getInstance();
//                int day = c.get(Calendar.DAY_OF_MONTH);
//                int month = c.get(Calendar.MONTH);
//                int year = c.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(SpendStatisticsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDateChoose.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, day_, month_, year_);
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
                    Toast.makeText(SpendStatisticsActivity.this,
                            "Vui lòng chọn ngày trước khi xem!"
                            , Toast.LENGTH_SHORT).show();
                }
                else {
                    nullTCKT.setText("");
                    String[] arrDate = txtDateChoose.getText().toString().split("/");
                    String day = arrDate[0];
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
                    if(listTKCT.size() != 0)
                    {
                        float totalChi = 0.0f;
                        for(int i = 0; i < listTKCT.size(); i++)
                        {
                            totalChi += listTKCT.get(i).getSo_tien_chi();
                        }
                        txtOutMoney.setText("-" + totalChi + " đ");
                        nullTCKT.setText("");
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

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(SpendStatisticsActivity.this, "You Clicked at " +name[+ position], Toast.LENGTH_SHORT).show();
//            }
//        });