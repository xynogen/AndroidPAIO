package com.polsri.paio.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.polsri.paio.dataClass.BeriMakan;
import com.polsri.paio.dataClass.Sensor;
import com.polsri.paio.dataClass.Settings;

public class settings extends AppCompatActivity {
    DatabaseReference database;
    EditText panjang, lebar, jarakKeDasar;
    Button btnRestart, btnSave;
    Settings set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        database = FirebaseDatabase.getInstance().getReference("/");
        panjang = (EditText) findViewById(R.id.etPanjang);
        lebar = (EditText) findViewById(R.id.etLebar);
        jarakKeDasar = (EditText) findViewById(R.id.etjarakKeDasar);
        set = new Settings();
        database.child("settings").child("restart").setValue("0");

        database.child("settings").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Error getting data on Settings" , Toast.LENGTH_SHORT ).show();
                }
                else {
                    set = task.getResult().getValue(Settings.class);
                    panjang.setText(set.getPanjang());
                    lebar.setText(set.getLebar());
                    jarakKeDasar.setText(set.getJarakKeDasar());
                }
            }
        });

        ValueEventListener setListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                set = snapshot.child("settings").getValue(Settings.class);
                panjang.setText(set.getPanjang());
                lebar.setText(set.getLebar());
                jarakKeDasar.setText(set.getJarakKeDasar());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), "Data Canceled on Sensor" , Toast.LENGTH_SHORT ).show();
            }
        };
        database.addValueEventListener(setListener);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set.setLebar(lebar.getText().toString());
                set.setPanjang(panjang.getText().toString());
                set.setJarakKeDasar(jarakKeDasar.getText().toString());
                set.setRestart("0");
                database.child("settings").setValue(set);
            }
        });

        Button setting = (Button) findViewById(R.id.btnRestart);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.child("settings").child("restart").setValue("1");
                Toast.makeText(getBaseContext(), "Device Restarted" , Toast.LENGTH_SHORT ).show();
            }
        });


    }
}