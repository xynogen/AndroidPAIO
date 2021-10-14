package com.polsri.paio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.polsri.paio.menu.beriMakan;
import com.polsri.paio.menu.kondisiAir;
import com.polsri.paio.menu.prediction;
import com.polsri.paio.menu.settings;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView food = (CardView) findViewById(R.id.food);
        CardView water = (CardView) findViewById(R.id.water);
        CardView prediction = (CardView) findViewById(R.id.prediction);
        CardView settings = (CardView) findViewById(R.id.settings);

        // Set Click Handler
        food.setOnClickListener(new ClickHandler());
        water.setOnClickListener(new ClickHandler());
        prediction.setOnClickListener(new ClickHandler());
        settings.setOnClickListener(new ClickHandler());

    }

    public class ClickHandler implements View.OnClickListener {
        Intent i;
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.food:
                    i = new Intent(getApplicationContext(), beriMakan.class);
                    break;
                case R.id.water:
                    i = new Intent(getApplicationContext(), kondisiAir.class);
                    break;
                case R.id.prediction:
                    i = new Intent(getApplicationContext(), prediction.class);
                    break;
                case R.id.settings:
                    i = new Intent(getApplicationContext(), settings.class);
                    break;
            }
            startActivity(i);
        }
    }
}