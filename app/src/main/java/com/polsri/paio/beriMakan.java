package com.polsri.paio;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class beriMakan extends AppCompatActivity {
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beri_makan);
        Button beriMakan = (Button) findViewById(R.id.beriMakan);
        database = FirebaseDatabase.getInstance().getReference("/");

        beriMakan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                database.child("switch").child("blower").setValue("1");
                Toast.makeText(getBaseContext(), "Blower Dinyalakan" , Toast.LENGTH_SHORT ).show();
            }
        });

        ValueEventListener swichListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Switch Switch = snapshot.child("switch").getValue(Switch.class);
                if (Switch.getPompa() == "1" || Switch.getBlower() == "1") {
                    beriMakan.setEnabled(false);
                    beriMakan.setClickable(false);
                    beriMakan.setBackgroundColor(getResources().getColor(R.color.gray));
                } else {
                    beriMakan.setEnabled(true);
                    beriMakan.setClickable(true);
                    beriMakan.setBackgroundColor(getResources().getColor(R.color.blue));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), "Data Canceled" , Toast.LENGTH_SHORT ).show();
            }
        };

        database.addValueEventListener(swichListener);
    }
}

