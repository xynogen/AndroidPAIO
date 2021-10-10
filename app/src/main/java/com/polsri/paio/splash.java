package com.polsri.paio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class splash extends AppCompatActivity {
    int DURATION = 1200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        ConstraintLayout appTitle = (ConstraintLayout) findViewById(R.id.appTitle);
        Animation slide = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        slide.setDuration(DURATION - 300);
        appTitle.setAnimation(slide);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(splash.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, DURATION);

    }
}