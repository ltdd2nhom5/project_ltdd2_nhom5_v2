package com.example.phuvo.recyclerview_v3;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    RecyclerView recyclerView;
    ImageView imvCar;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Product> data;
    MyCustomAdapter adapter;
    Intent intent;
    SensorManager sensorManager;
    public static boolean isMainActivity = true;
    long lastUpdate;
    SensorEventListener proximitySensorListener;
    Sensor proximitySensor;
    Animation blink,slideDown,move_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        imvCar = (ImageView)findViewById(R.id.imvCar);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new MyDB().product_list;
        adapter = new MyCustomAdapter(data);
        recyclerView.setAdapter(adapter);

        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();




        cam_bien_tiem_can();




    }

    private void cam_bien_tiem_can() {
         proximitySensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(proximitySensor == null) {
            Toast.makeText(this, "Cảm biến ko tồn tại", Toast.LENGTH_SHORT).show();
//            finish(); // Close app
        }
        // Create listener
         proximitySensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // More code goes here
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

// Register it, specifying the polling interval in
// microseconds
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuCart){
            intent = new Intent(MainActivity.this,CartActivity.class);
            startActivity(intent);
//            Toast.makeText(this, "cart ne", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            switchActivity(event);
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

    private void switchActivity(SensorEvent event) {

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
            if (isMainActivity) {
                intent = new Intent(MainActivity.this,CartActivity.class);
//                isMainActivity = !isMainActivity;
                startActivity(intent);
                Toast.makeText(this, "switch to Cart" + isMainActivity, Toast.LENGTH_SHORT).show();
            }
            else{
//                isMainActivity = !isMainActivity;
                this.finish();
                Toast.makeText(this, "switch to Main" + isMainActivity, Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(proximitySensorListener,
                proximitySensor, 2 * 1000 * 1000);
        slideDown = AnimationUtils.loadAnimation(recyclerView.getContext(),R.anim.slide_down);

        move_car = AnimationUtils.loadAnimation(imvCar.getContext(),R.anim.move_car);
        imvCar.startAnimation(move_car);
        recyclerView.startAnimation(slideDown);



    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
