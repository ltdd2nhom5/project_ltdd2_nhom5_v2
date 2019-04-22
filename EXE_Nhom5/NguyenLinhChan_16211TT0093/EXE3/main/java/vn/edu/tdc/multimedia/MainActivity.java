package vn.edu.tdc.multimedia;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    GifImageView gif, ballfig;
    Context context;
    Button l_t_r, r_t_l, blink, bounce, rotate, sample, fadein, zoomout, mixed, multimeedia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gif = (GifImageView) findViewById(R.id.icon);
        ballfig = (GifImageView) findViewById(R.id.ball);
        ballfig.setBackground(null);
        gif.setBackground(null);
        l_t_r = (Button)findViewById(R.id.left_t_r);
        r_t_l = (Button)findViewById(R.id.right_t_l);
        blink = (Button)findViewById(R.id.blink);
        bounce = (Button)findViewById(R.id.bounce);
        rotate = (Button)findViewById(R.id.rotate);
        sample = (Button)findViewById(R.id.sample);
        fadein = (Button)findViewById(R.id.faceIn);
        zoomout = (Button)findViewById(R.id.zoomout);
        mixed = (Button)findViewById(R.id.mixed);
        multimeedia = (Button)findViewById(R.id.SunActivity);

        multimeedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SunActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        l_t_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gif.setImageResource(R.drawable.left_to_right);
                gif.setBackground(null);
                Animation icon = AnimationUtils.loadAnimation(MainActivity.this,R.anim.lefttoright);
                Animation ball = AnimationUtils.loadAnimation(MainActivity.this,R.anim.lefttoright);
                gif.startAnimation(icon);
                ballfig.startAnimation(ball);
            }
        });
        r_t_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gif.setImageResource(R.drawable.right_to_left);
                Animation icon = AnimationUtils.loadAnimation(MainActivity.this,R.anim.righttoleft);
                gif.startAnimation(icon);
            }
        });
        blink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation icon = AnimationUtils.loadAnimation(MainActivity.this,R.anim.blink_anim);
                gif.startAnimation(icon);
            }
        });
        bounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation icon = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);
                gif.startAnimation(icon);
            }
        });
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation icon = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate);
                gif.startAnimation(icon);
            }
        });
        sample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation icon = AnimationUtils.loadAnimation(MainActivity.this,R.anim.sample_anim);
                gif.startAnimation(icon);
            }
        });
        zoomout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation icon = AnimationUtils.loadAnimation(MainActivity.this,R.anim.zoomout);
                gif.startAnimation(icon);
            }
        });
        fadein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation icon = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fadein);
                gif.startAnimation(icon);
            }
        });
        mixed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation icon = AnimationUtils.loadAnimation(MainActivity.this,R.anim.mixed_anim);
                gif.startAnimation(icon);
            }
        });
    }
}
