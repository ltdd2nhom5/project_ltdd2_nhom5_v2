package com.example.phuvo.recyclerview_v3;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements SensorEventListener {
    ArrayList<Product> data;
    Animation move_total_price,rotate_chong_chong;
    ImageView imvChongChong;
    MyCustomAdapterCart adapter;
    RecyclerView recyclerView;
    TextView txtTotal;
    RecyclerView.LayoutManager layoutManager;
    SensorManager sensorManager;
    long lastUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_cart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        txtTotal = (TextView)findViewById(R.id.txtTotal_cart_item);
        imvChongChong = (ImageView)findViewById(R.id.imvChongChong);
        move_total_price = AnimationUtils.loadAnimation(txtTotal.getContext(),R.anim.move_total_price);
        rotate_chong_chong = AnimationUtils.loadAnimation(imvChongChong.getContext(),R.anim.rotate_chong_chong);
        txtTotal.setText("Tổng tiền: "+MyDB.getTotal()+"$");
        imvChongChong.startAnimation(rotate_chong_chong);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            backToHome(event);
        }
//        if(event.values[0] < proximitySensor.getMaximumRange()) {
//            // Detected something nearby
//            Toast.makeText(this, "Detected something nearby", Toast.LENGTH_SHORT).show();
//            getWindow().getDecorView().setBackgroundColor(Color.RED);
//        } else {
//            // Nothing is nearby
//            Toast.makeText(this, "Nothing is nearby", Toast.LENGTH_SHORT).show();
//            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
//        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void backToHome(SensorEvent event) {

        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

        long actualTime = System.currentTimeMillis();
//        Toast.makeText(getApplicationContext(),String.valueOf(accelationSquareRoot)+" "+
//                SensorManager.GRAVITY_EARTH,Toast.LENGTH_SHORT).show();
        if (accelationSquareRoot >= 5) //it will be executed if you shuffle
        {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;//updating lastUpdate for next shuffle
            this.finish();
        }
    }

    @Override
    protected void onResume() {

        super.onResume();
        data = new ArrayList<>();
        for (int i = 0; i < MyDB.product_list.size(); i++) {
            for (int item : MyDB.cart_items) {
                if (item == i) {
                    data.add(MyDB.product_list.get(i));
                }
            }
        }
        adapter = new MyCustomAdapterCart(data);
        recyclerView.setAdapter(adapter);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

//        Toast.makeText(this, "on resume", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
