package com.polsri.paio.menu;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
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
import com.polsri.paio.dataClass.Switch;

public class beriMakan extends AppCompatActivity {
    DatabaseReference database;
    Button beriMakan, btnTimerPakan, btnBeratPakan;
    Dialog numberPickerDialog;
    TextView tvTimerPakan, tvBeratPakan, tvBeratSekarang;
    int timerPakan, beratPakan;
    BeriMakan BeriMakan;
    Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beri_makan);
        beriMakan = (Button) findViewById(R.id.beriMakan);
        tvTimerPakan = (TextView) findViewById(R.id.tvTimerPakan);
        tvBeratPakan = (TextView) findViewById(R.id.tvBeratPakan);
        tvBeratSekarang = (TextView) findViewById(R.id.tvBeratSekarang);
        timerPakan = 0;
        beratPakan = 0;
        database = FirebaseDatabase.getInstance().getReference("/");
        numberPickerDialog = new Dialog(this);
        BeriMakan = new BeriMakan();
        sw = new Switch("0", "0", "0");
        database.child("switch").setValue(sw);

        database.child("berimakan").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Error getting data on BeriMakan" , Toast.LENGTH_SHORT ).show();
                }
                else {
                    BeriMakan = task.getResult().getValue(BeriMakan.class);
                    tvBeratPakan.setText(BeriMakan.getBeratPakan().concat(" Kg"));
                    tvTimerPakan.setText(BeriMakan.getTimerPakan().concat(" Jam"));
                    tvBeratSekarang.setText(BeriMakan.getBeratSekarang().concat(" Kg"));
                }
            }
        });

        ValueEventListener berimakan = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BeriMakan = snapshot.child("berimakan").getValue(BeriMakan.class);
                tvBeratPakan.setText(BeriMakan.getBeratPakan().concat(" Kg"));
                tvTimerPakan.setText(BeriMakan.getTimerPakan().concat(" Jam"));
                tvBeratSekarang.setText(BeriMakan.getBeratSekarang().concat(" Kg"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), "Data Canceled on BeriMakan" , Toast.LENGTH_SHORT ).show();
            }
        };
        database.addValueEventListener(berimakan);

        beriMakan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int beratSekarang = (int) Integer.parseInt(BeriMakan.getBeratSekarang()) - 1;
                BeriMakan.setBeratSekarang(String.valueOf(beratSekarang));
                database.child("berimakan").setValue(BeriMakan);
                database.child("switch").child("blower").setValue("1");
                Toast.makeText(getBaseContext(), "Blower Dinyalakan" , Toast.LENGTH_SHORT ).show();
            }
        });

        btnTimerPakan = (Button) findViewById(R.id.btnTimerPakan);
        btnTimerPakan.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                numberPickerDialog.setContentView(R.layout.number_picker_pop);
                NumberPicker Select = (NumberPicker) numberPickerDialog.findViewById(R.id.npSelect);
                Button btnAtur = (Button) numberPickerDialog.findViewById(R.id.btnAtur);
                btnAtur.setText("Atur");
                numberPickerDialog.show();
                Select.setMaxValue(24);
                Select.setMinValue(1);
                Select.setValue(Integer.parseInt(BeriMakan.getTimerPakan()));
                btnAtur.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timerPakan = Select.getValue();
                        tvTimerPakan.setText(String.valueOf(timerPakan).concat(" Jam"));
                        BeriMakan.setTimerPakan(String.valueOf(timerPakan));
                        database.child("berimakan").setValue(BeriMakan);
                        numberPickerDialog.dismiss();
                    }
                });
            }
        });

        btnBeratPakan = (Button) findViewById(R.id.btnBeratPakan);
        btnBeratPakan.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                numberPickerDialog.setContentView(R.layout.number_picker_pop);
                NumberPicker Select = (NumberPicker) numberPickerDialog.findViewById(R.id.npSelect);
                Button btnAtur = (Button) numberPickerDialog.findViewById(R.id.btnAtur);
                btnAtur.setText("Masukkan");
                numberPickerDialog.show();
                Select.setMaxValue(100);
                Select.setMinValue(1);
                Select.setValue(Integer.parseInt(BeriMakan.getBeratPakan()));
                btnAtur.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        beratPakan = Select.getValue();
                        tvBeratPakan.setText(String.valueOf(beratPakan).concat(" Kg"));
                        BeriMakan.setBeratPakan(String.valueOf(beratPakan));
                        BeriMakan.setBeratSekarang(String.valueOf(beratPakan));
                        database.child("berimakan").setValue(BeriMakan);
                        numberPickerDialog.dismiss();
                    }
                });
            }
        });

        ValueEventListener timerPakan = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Switch Switch = snapshot.child("switch").getValue(Switch.class);
                if (Switch.getPompa() == "1" || Switch.getBlower() == "1" || Switch.getPompa2() == "1") {
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
                Toast.makeText(getBaseContext(), "Data Canceled on Switch" , Toast.LENGTH_SHORT ).show();
            }
        };


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

