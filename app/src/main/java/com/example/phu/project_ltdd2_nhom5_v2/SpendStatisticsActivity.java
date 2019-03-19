package com.example.phu.project_ltdd2_nhom5_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SpendStatisticsActivity extends AppCompatActivity {
    Button btn1, btn2, btn3;
    RelativeLayout viewStatistic;
    Intent intent;
    ListView list;
    String[] name = {
            "Ăn uống",
            "Đi lại",
            "Mua sắm",
            "Y tế",
            "Sinh hoạt",
    } ;
    Integer[] imageId = {
            R.drawable.glass,
            R.drawable.move,
            R.drawable.shopping,
            R.drawable.health,
            R.drawable.family,
    };
    String[] money = {
      "50,000",
      "50,000",
      "30,000",
      "20,000",
      "50,000",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenditure_statistics);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        CustomList adapter = new CustomList(SpendStatisticsActivity.this, name, imageId, money);
        list = (ListView) findViewById(R.id.listItem);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(SpendStatisticsActivity.this, "You Clicked at ", Toast.LENGTH_SHORT).show();
                Toast.makeText(SpendStatisticsActivity.this, "You Clicked at " +name[+ position], Toast.LENGTH_SHORT).show();
            }
        });

        viewStatistic = (RelativeLayout) findViewById(R.id.viewStatis);
        viewStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SpendStatisticsActivity.this, ThemKhoanThuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }

}
