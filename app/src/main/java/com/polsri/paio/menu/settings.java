package com.polsri.paio.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.polsri.paio.R;

public class settings extends AppCompatActivity {
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance().getReference("/");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button setting = (Button) findViewById(R.id.restart);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.child("restart").setValue("1");
                Toast.makeText(getBaseContext(), "Device Restarted" , Toast.LENGTH_SHORT ).show();
            }
        });


    }
}