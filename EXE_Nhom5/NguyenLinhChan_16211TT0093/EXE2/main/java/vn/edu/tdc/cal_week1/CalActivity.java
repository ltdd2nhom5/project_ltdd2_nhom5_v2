package vn.edu.tdc.cal_week1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nguyenvanquan7826.com.Balan;

public class CalActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvMath;
    private TextView tvResult;

    private int[] idButton = {
            R.id.btn0,
            R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnPoint,
            R.id.btnCong, R.id.btnTru, R.id.btnNhan, R.id.btnChia,
            R.id.btnOpen, R.id.btnClose,
            R.id.btnClean, R.id.btnResult
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_layout);

        connectView();
    }

    private void connectView() {
        tvMath = (TextView) findViewById(R.id.tvMath);
        tvResult = (TextView) findViewById(R.id.tvResult);

        for (int i = 0; i < idButton.length; i++) {
            findViewById(idButton[i]).setOnClickListener(this);
        }
        init();
    }

    private void init() {
        tvMath.setText("|");
        tvResult.setText("0");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        // check button number and button operator
        for (int i = 0; i < idButton.length - 2; i++) {
            if (id == idButton[i]) {
                String text = ((Button) findViewById(id)).getText().toString();

                // clear char | on top
                if (tvMath.getText().toString().trim().equals("|")) {
                    tvMath.setText("");
                }

                tvMath.append(text);
                return;
            }
        }

        // clear screen
        if (id == R.id.btnClean) {
            init();
            return;
        }

        // calculation
        if (id == R.id.btnResult) {
            cal();
        }
    }

    private void cal() {
        try {
            String math = tvMath.getText().toString().trim();
            if (math.length() > 0) {
                Balan balan = new Balan();
                String result = balan.valueMath(math) + "";
                String error = balan.getError();

                // check error
                if (error != null) {
                    tvResult.setText(error);

                } else { // show result
                    tvResult.setText(result);
                }
            }
        }catch (Exception e)
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            init();
        }
    }
}
