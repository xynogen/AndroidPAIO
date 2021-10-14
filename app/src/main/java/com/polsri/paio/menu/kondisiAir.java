package com.polsri.paio.menu;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.polsri.paio.R;
import com.polsri.paio.dataClass.Sensor;
import com.polsri.paio.dataClass.Switch;


public class kondisiAir extends AppCompatActivity {
    private String derajat = "°C";
    private String kubik = "m³";
    private TextView PH, Turbidity, Temp, Volume;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kondisi_air);

        PH = (TextView) findViewById(R.id.phAir);
        Turbidity = (TextView) findViewById(R.id.turbidity);
        Temp = (TextView) findViewById(R.id.temp);
        Volume = (TextView) findViewById(R.id.volume);

        database = FirebaseDatabase.getInstance().getReference("/");
        Button siramTanaman = (Button) findViewById(R.id.siramTanaman);

        ValueEventListener sensorListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Sensor sensor = snapshot.child("data").getValue(Sensor.class);
                PH.setText(sensor.getPH());
                Turbidity.setText(sensor.getTurbidity().concat(" NTU"));
                Temp.setText(sensor.getTemp().concat(derajat));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), "Data Canceled on Sensor" , Toast.LENGTH_SHORT ).show();
            }
        };
        database.addValueEventListener(sensorListener);

        ValueEventListener switchListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Switch Switch = snapshot.child("switch").getValue(Switch.class);
                if (Switch.getPompa() == "1" || Switch.getBlower() == "1") {
                    siramTanaman.setEnabled(false);
                    siramTanaman.setClickable(false);
                    siramTanaman.setBackgroundColor(getResources().getColor(R.color.gray));
                } else {
                    siramTanaman.setEnabled(true);
                    siramTanaman.setClickable(true);
                    siramTanaman.setBackgroundColor(getResources().getColor(R.color.blue));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), "Data Canceled on Switch" , Toast.LENGTH_SHORT ).show();
            }
        };
        database.addValueEventListener(switchListener);

        siramTanaman.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                database.child("switch").child("pompa").setValue("1");
                Toast.makeText(getBaseContext(), "Pompa Dinyalakan" , Toast.LENGTH_SHORT ).show();
            }
        });
    }
}