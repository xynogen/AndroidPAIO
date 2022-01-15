package com.polsri.paio.menu;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.polsri.paio.R;
import com.polsri.paio.dataClass.BeriMakan;
import com.polsri.paio.dataClass.SettingPendapatan;
import com.polsri.paio.dataClass.SettingsBiaya;
import com.polsri.paio.dataClass.SettingsDate;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class prediction extends AppCompatActivity {
    Dialog datePickerDialog;
    SettingsDate settingsDate;
    SettingsBiaya settingsBiaya;
    SettingPendapatan settingPendapatan;
    String url = "https://autofish-d31e9-default-rtdb.asia-southeast1.firebasedatabase.app/";
    DatabaseReference database = FirebaseDatabase.getInstance(url).getReference("/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        datePickerDialog = new Dialog(this);

        Button btnTanggalPembudidayaan = (Button) findViewById(R.id.btnTanggalPembudidayaan);
        TextView tvTanggalPembudidayaan = (TextView) findViewById(R.id.tvTanggalPembudidayaan);
        btnTanggalPembudidayaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.setContentView(R.layout.date_picker_pop);
                DatePicker Select = (DatePicker) datePickerDialog.findViewById(R.id.npSelect);
                Select.setMaxDate(Calendar.getInstance().getTimeInMillis());
                Button btnAtur = (Button) datePickerDialog.findViewById(R.id.btnAtur);
                datePickerDialog.show();

                btnAtur.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tanggal = String.valueOf(Select.getDayOfMonth()) + "/" +
                                String.valueOf(Select.getMonth()+1) + "/" +
                                String.valueOf(Select.getYear());
                        tvTanggalPembudidayaan.setText(tanggal);
                        datePickerDialog.dismiss();
                    }
                });
            }
        });

        Button btntTanggalTerjual = (Button) findViewById(R.id.btnTanggalTerjual);
        TextView tvTanggalTerjual = (TextView) findViewById(R.id.tvTanggalTerjual);
        TextView SelisihHari = (TextView) findViewById(R.id.tvSelisihHari);
        btntTanggalTerjual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.setContentView(R.layout.date_picker_pop);
                DatePicker Select = (DatePicker) datePickerDialog.findViewById(R.id.npSelect);
                String tanggalPembudidayaanStr = tvTanggalPembudidayaan.getText().toString();
                Date minDate = null;
                try
                {
                    minDate = new SimpleDateFormat("d/M/yyyy").parse(tanggalPembudidayaanStr);
                }
                catch (Exception e) {
                    minDate = Calendar.getInstance().getTime();

                }
                Select.setMinDate(minDate.getTime());
                Button btnAtur = (Button) datePickerDialog.findViewById(R.id.btnAtur);
                datePickerDialog.show();

                btnAtur.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tanggal = String.valueOf(Select.getDayOfMonth()) + "/" +
                                String.valueOf(Select.getMonth()+1) + "/" +
                                String.valueOf(Select.getYear());
                        tvTanggalTerjual.setText(tanggal);

                        String tanggalPembudidayaanStr = tvTanggalPembudidayaan.getText().toString();
                        String tanggalTerjualStr = tvTanggalTerjual.getText().toString();

                        SettingsDate settingsTanggal = new SettingsDate(tanggalPembudidayaanStr, tanggalTerjualStr);


                        SelisihHari.setText(String.valueOf(settingsTanggal.getDayDifference()).concat(" Hari"));
                        datePickerDialog.dismiss();
                    }
                });
            }
        });

        EditText biayaPakan = (EditText) findViewById(R.id.etBiayaPakan);
        EditText biayaTenagaKerja = (EditText) findViewById(R.id.etBiayaTenagaKerja);
        EditText biayaPembibitan = (EditText) findViewById(R.id.etBiayaPembibitan);
        EditText biayaPerawatan = (EditText) findViewById(R.id.etBiayaPerawatan);
        EditText biayaOverhead = (EditText) findViewById(R.id.etBiayaOverhead);
        EditText biayaUtilitas = (EditText) findViewById(R.id.etBiayaUtilitas);
        EditText biayaDistribusiPemasaran = (EditText) findViewById(R.id.etBiayaDistribusiPerawatan);
        EditText jumlahPanen = (EditText) findViewById(R.id.etJumlahPanen);
        EditText hargaJualProduk = (EditText) findViewById(R.id.etHargaJualProduk);
        Button hitung = (Button) findViewById(R.id.hitung);
        TextView hasil = (TextView) findViewById(R.id.hasil);

        database.child("prediction").child("date").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(getBaseContext(), task.getException().toString(), Toast.LENGTH_SHORT ).show();
                }
                else {
                    settingsDate = task.getResult().getValue(SettingsDate.class);
                    tvTanggalPembudidayaan.setText(settingsDate.getTanggalPembudidayaan());
                    tvTanggalTerjual.setText(settingsDate.getTanggaTerjual());
                    SelisihHari.setText(String.valueOf(settingsDate.getDayDifference()).concat(" Hari"));
                }
            }
        });

        database.child("prediction").child("biaya").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(getBaseContext(), task.getException().toString(), Toast.LENGTH_SHORT ).show();
                }
                else {
                    settingsBiaya = task.getResult().getValue(SettingsBiaya.class);
                    biayaPakan.setText(settingsBiaya.getBiayaPakan());
                    biayaTenagaKerja.setText(settingsBiaya.getBiayaTenagaKerja());
                    biayaPembibitan.setText(settingsBiaya.getBiayaPembibitan());
                    biayaPerawatan.setText(settingsBiaya.getBiayaPerawatan());
                    biayaOverhead.setText(settingsBiaya.getBiayaOverhead());
                    biayaUtilitas.setText(settingsBiaya.getBiayaUtilitas());
                    biayaDistribusiPemasaran.setText(settingsBiaya.getBiayaDistribusiPemasaran());
                }
            }
        });

        database.child("prediction").child("pendapatan").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(getBaseContext(), task.getException().toString(), Toast.LENGTH_SHORT ).show();
                }
                else {
                    settingPendapatan = task.getResult().getValue(SettingPendapatan.class);
                    jumlahPanen.setText(settingPendapatan.getJumlahPanen());
                    hargaJualProduk.setText(settingPendapatan.getHargaJualProduk());
                }
            }
        });


        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggalPembudidayaanStr = tvTanggalPembudidayaan.getText().toString();
                String tanggalTerjualStr = tvTanggalTerjual.getText().toString();
                settingsDate = new SettingsDate(tanggalPembudidayaanStr, tanggalTerjualStr);
                int dayDifference = settingsDate.getDayDifference();

                String biayaPakanInt = biayaPakan.getText().toString();
                String biayaTenagaKerjaInt = biayaTenagaKerja.getText().toString();
                String biayaPembibitanInt = biayaPembibitan.getText().toString();
                String biayaPerawatanInt = biayaPerawatan.getText().toString();
                String biayaOverheadInt = biayaOverhead.getText().toString();
                String biayaUtilitasInt = biayaUtilitas.getText().toString();
                String biayaDistribusiPemasaranInt = biayaDistribusiPemasaran.getText().toString();

                settingsBiaya = new SettingsBiaya(biayaPakanInt, biayaTenagaKerjaInt, biayaPembibitanInt,
                        biayaPerawatanInt, biayaOverheadInt, biayaUtilitasInt, biayaDistribusiPemasaranInt);

                String jumlahPanenStr = jumlahPanen.getText().toString();
                String hargaJualProdukStr = hargaJualProduk.getText().toString();
                settingPendapatan = new SettingPendapatan(jumlahPanenStr, hargaJualProdukStr);

                try {
                    database.child("prediction").child("date").setValue(settingsDate);
                    database.child("prediction").child("biaya").setValue(settingsBiaya);
                    database.child("prediction").child("pendapatan").setValue(settingPendapatan);
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Data Gagal Dikirim" , Toast.LENGTH_SHORT ).show();
                }


                double hariBEP = (settingsBiaya.getTotalBiaya() * 1.0 / settingPendapatan.getTotalKeuntungan() * 1.0 ) * (dayDifference * 1.0);
                hasil.setText(String.format("%.1f%n Hari", hariBEP));

            }
        });

    };

}