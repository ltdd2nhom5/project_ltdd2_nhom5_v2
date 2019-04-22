package com.example.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    //sensor
    private SensorManager sensorManager;
    private boolean isColor = false;
    private long lastUpdate;
    //randow so xuc xac
    public static final Random RANDOM = new Random();
    private ImageView img1, img2;
    private Button play;
    private TextView txtGoal;
    //phat media
    private SoundPool soundPool;
    private AudioManager audioManager;
    // Số luồng âm thanh phát ra tối đa.
    private static final int MAX_STREAMS = 5;
    // Chọn loại luồng âm thanh để phát nhạc.
    private static final int streamType = AudioManager.STREAM_ALARM;

    private boolean loaded;

    private int soundIdGun;
    private float volume;

    public static int randomDiceValue() {
        return RANDOM.nextInt(6) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button)findViewById(R.id.btnDice);
        img1 = (ImageView)findViewById(R.id.imgView1);
        img2 = (ImageView)findViewById(R.id.imgView2);
        txtGoal = (TextView)findViewById(R.id.txtGoal);

        //media
        // Đối tượng AudioManager sử dụng để điều chỉnh âm lượng.
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Chỉ số âm lượng hiện tại của loại luồng nhạc cụ thể (streamType).
        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);

        // Chỉ số âm lượng tối đa của loại luồng nhạc cụ thể (streamType).
        float maxVolumeIndex  = (float) audioManager.getStreamMaxVolume(streamType);

        // Âm lượng  (0 --> 1)
        this.volume = currentVolumeIndex / maxVolumeIndex;

        // Cho phép thay đổi âm lượng các luồng kiểu 'streamType' bằng các nút
        // điều khiển của phần cứng.
        this.setVolumeControlStream(streamType);

        // Với phiên bản Android SDK >= 21
        if (Build.VERSION.SDK_INT >= 21 ) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            soundPool = builder.build();
        }
        // Với phiên bản Android SDK < 21
        else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_ALARM, 0);
        }

        // Sự kiện SoundPool đã tải lên bộ nhớ thành công.
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        //tải nhac
        soundIdGun = soundPool.load(MainActivity.this, R.raw.b1, 1);
        /////////

        play.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if(loaded)  {
                    float leftVolumn = volume;
                    float rightVolumn = volume;
                    // Phát âm thanh tiếng súng. Trả về ID của luồng mới phát ra.
                    int streamId = MainActivity.this.soundPool.play(MainActivity.this.soundIdGun,leftVolumn, rightVolumn, 1, 0, 1f);
                    Toast.makeText(MainActivity.this, "Playing", Toast.LENGTH_LONG).show();
                }

                final Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.clockwise);
                final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int val1 = randomDiceValue();
                        int val2 = randomDiceValue();
                        int res1 = getResources().getIdentifier("dice_" + val1, "mipmap", "com.example.myapplication");
                        int res2 = getResources().getIdentifier("dice_" + val2, "mipmap", "com.example.myapplication");
                        int num1 = val1, num2 = val2;

                        if (animation == anim){
                            img1.setImageResource(res1);
                            img2.setImageResource(res2);
                        }
                        txtGoal.setText(num1 + num2 + "");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                };

                anim.setAnimationListener(animationListener);

                img1.startAnimation(anim);
                img2.startAnimation(anim);
            }
        });
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
// Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

        long actualTime = System.currentTimeMillis();
//        Toast.makeText(getApplicationContext(),String.valueOf(accelationSquareRoot)+" "+
//                SensorManager.GRAVITY_EARTH,Toast.LENGTH_SHORT).show();

        if (accelationSquareRoot >= 2) //it will be executed if you shuffle
        {

            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;//updating lastUpdate for next shuffle
            if (isColor) {
                final Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.clockwise);

                final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int val1 = randomDiceValue();
                        int val2 = randomDiceValue();
                        int res1 = getResources().getIdentifier("dice_" + val1, "mipmap", "com.example.myapplication");
                        int res2 = getResources().getIdentifier("dice_" + val2, "mipmap", "com.example.myapplication");
                        int num1 = val1, num2 = val2;

                        if (animation == anim){
                            img1.setImageResource(res1);
                            img2.setImageResource(res2);
                        }
                        txtGoal.setText(num1 + num2 + "");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                };

                anim.setAnimationListener(animationListener);

                img1.startAnimation(anim);
                img2.startAnimation(anim);
            }

            isColor = !isColor;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(MainActivity.this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(MainActivity.this);
    }
}
