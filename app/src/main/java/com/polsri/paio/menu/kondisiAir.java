package com.polsri.paio.menu;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.polsri.paio.R;
import com.polsri.paio.dataClass.Sensor;
import com.polsri.paio.dataClass.Switch;
import com.polsri.paio.dataClass.Settings;
import com.polsri.paio.menu.CameraView;


public class kondisiAir extends AppCompatActivity {
    private String derajat = "°C";
    private String kubik = "m³";
    private int panjang, lebar, jarakKeDasar;
    private TextView PH, Turbidity, Temp, Volume;
    private DatabaseReference database;
    private Switch sw;
    private Settings set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kondisi_air);
        database = FirebaseDatabase.getInstance().getReference("/");

        sw = new Switch("0", "0", "0");
        database.child("switch").setValue(sw);

        PH = (TextView) findViewById(R.id.phAir);
        Turbidity = (TextView) findViewById(R.id.turbidity);
        Temp = (TextView) findViewById(R.id.temp);
        Volume = (TextView) findViewById(R.id.volume);


        set = new Settings();

        database.child("settings").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Error getting data on Settings" , Toast.LENGTH_SHORT ).show();
                }
                else {
                    set = task.getResult().getValue(Settings.class);
                    panjang = Integer.valueOf(set.getPanjang());
                    lebar = Integer.valueOf(set.getLebar());
                    jarakKeDasar = Integer.valueOf(set.getJarakKeDasar());
                }
            }
        });


        ValueEventListener sensorListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Sensor sensor = snapshot.child("data").getValue(Sensor.class);
                set = snapshot.child("settings").getValue(Settings.class);
                PH.setText(sensor.getPH());
                Turbidity.setText(sensor.getTurbidity().concat(" NTU"));
                Temp.setText(sensor.getTemp().concat(derajat));
                int tinggi = (int) (jarakKeDasar - Integer.parseInt(sensor.getDistance()))/100;
                int volume = (int) panjang * lebar * tinggi;
                Volume.setText(String.valueOf(volume).concat(kubik));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), "Data Canceled on Sensor" , Toast.LENGTH_SHORT ).show();
            }
        };
        database.addValueEventListener(sensorListener);

        Button siramTanaman = (Button) findViewById(R.id.siramTanaman);
        siramTanaman.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                database.child("switch").child("pompa").setValue("1");
                Toast.makeText(getBaseContext(), "Pompa Dinyalakan" , Toast.LENGTH_SHORT ).show();
            }
        });
        Button aliriTanaman = (Button) findViewById(R.id.aliriTanaman);
        aliriTanaman.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                database.child("switch").child("pompa2").setValue("1");
                Toast.makeText(getBaseContext(), "Pompa Dinyalakan" , Toast.LENGTH_SHORT ).show();
            }
        });

        ValueEventListener switchListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Switch Switch = snapshot.child("switch").getValue(Switch.class);
                if (Switch.getPompa() == "1" || Switch.getBlower() == "1" || Switch.getPompa2() == "1") {
                    siramTanaman.setEnabled(false);
                    siramTanaman.setClickable(false);
                    siramTanaman.setBackgroundColor(getResources().getColor(R.color.gray));
                    aliriTanaman.setEnabled(false);
                    aliriTanaman.setClickable(false);
                    aliriTanaman.setBackgroundColor(getResources().getColor(R.color.gray));
                } else {
                    siramTanaman.setEnabled(true);
                    siramTanaman.setClickable(true);
                    siramTanaman.setBackgroundColor(getResources().getColor(R.color.blue));
                    aliriTanaman.setEnabled(true);
                    aliriTanaman.setClickable(true);
                    aliriTanaman.setBackgroundColor(getResources().getColor(R.color.blue));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), "Data Canceled on Switch" , Toast.LENGTH_SHORT ).show();
            }
        };
        database.addValueEventListener(switchListener);

        Button btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            Intent i;
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), CameraView.class);
                startActivity(i);
            }
        });


    }
}