package com.trycatch.hashtagfortiktok.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.trycatch.hashtagfortiktok.R;


public class SplashScreen extends AppCompatActivity {

    private TextView textView;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        textView = findViewById(R.id.ss_tv);
        linearLayout = findViewById(R.id.ss_ll);

        textView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.blink));
        linearLayout.setAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_down));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }
}
