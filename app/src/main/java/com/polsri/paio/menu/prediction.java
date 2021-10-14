package com.polsri.paio.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.polsri.paio.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class prediction extends AppCompatActivity {
    Dialog datePickerDialog;
    Date datePembudidayaan, dateTerjual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        datePickerDialog = new Dialog(this);

        EditText tanggalPembudidayaan = (EditText) findViewById(R.id.etTanggalPembudidayaan);
        tanggalPembudidayaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.setContentView(R.layout.date_picker_pop);
                DatePicker Select = (DatePicker) datePickerDialog.findViewById(R.id.npSelect);
                Button btnAtur = (Button) datePickerDialog.findViewById(R.id.btnAtur);
                datePickerDialog.show();

                btnAtur.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tanggal = String.valueOf(Select.getDayOfMonth()) + "/" +
                                String.valueOf(Select.getMonth()) + "/" +
                                String.valueOf(Select.getYear());
                        tanggalPembudidayaan.setText(tanggal);
                        datePickerDialog.dismiss();
                    }
                });
            }
        });

        EditText tanggaTerjual = (EditText) findViewById(R.id.etTanggalTerjual);
        tanggaTerjual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.setContentView(R.layout.date_picker_pop);
                DatePicker Select = (DatePicker) datePickerDialog.findViewById(R.id.npSelect);
                Button btnAtur = (Button) datePickerDialog.findViewById(R.id.btnAtur);
                datePickerDialog.show();

                btnAtur.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tanggal = String.valueOf(Select.getDayOfMonth()) + "/" +
                                String.valueOf(Select.getMonth()) + "/" +
                                String.valueOf(Select.getYear());
                        tanggaTerjual.setText(tanggal);
                        datePickerDialog.dismiss();
                    }
                });
            }
        });

        EditText biayaPakan = (EditText) findViewById(R.id.etBiayaPakan);
        biayaPakan.setOnClickListener(new clickHandler());

        EditText biayaTenagaKerja = (EditText) findViewById(R.id.etBiayaTenagaKerja);
        biayaTenagaKerja.setOnClickListener(new clickHandler());

        EditText biayaPembibitan = (EditText) findViewById(R.id.etBiayaPembibitan);
        biayaPembibitan.setOnClickListener(new clickHandler());

        EditText biayaPerawatan = (EditText) findViewById(R.id.etBiayaPerawatan);
        biayaPerawatan.setOnClickListener(new clickHandler());

        EditText biayaOverhead = (EditText) findViewById(R.id.etBiayaOverhead);
        biayaOverhead.setOnClickListener(new clickHandler());

        EditText biayaUtilitas = (EditText) findViewById(R.id.etBiayaUtilitas);
        biayaUtilitas.setOnClickListener(new clickHandler());

        EditText biayaDistribusiPemasaran = (EditText) findViewById(R.id.etBiayaDistribusiPerawatan);
        biayaDistribusiPemasaran.setOnClickListener(new clickHandler());

        EditText jumlahPanen = (EditText) findViewById(R.id.etJumlahPanen);
        jumlahPanen.setOnClickListener(new clickHandler());

        EditText hargaJualProduk = (EditText) findViewById(R.id.etHargaJualProduk);
        hargaJualProduk.setOnClickListener(new clickHandler());

        Button hitung = (Button) findViewById(R.id.hitung);

        TextView hasil = (TextView) findViewById(R.id.hasil);

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggalPembudidayaanStr = tanggalPembudidayaan.getText().toString();
                String tanggalTerjualStr = tanggaTerjual.getText().toString();

                Date datePembudidayaan = null;
                Date dateProduk = null;

                try
                {
                    datePembudidayaan = new SimpleDateFormat("d/M/yyyy").parse(tanggalPembudidayaanStr);
                    dateProduk = new SimpleDateFormat("d/M/yyyy").parse(tanggalTerjualStr);
                }
                catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Format Tanggal Salah" , Toast.LENGTH_SHORT ).show();
                }

                long timeDiff = dateProduk.getTime() - datePembudidayaan.getTime();
                int dayDifference = (int) ((timeDiff / (1000*60*60*24)) % 365);



                int biayaPakanInt = Integer.parseInt(biayaPakan.getText().toString());
                int biayaTenagaKerjaInt = Integer.parseInt(biayaTenagaKerja.getText().toString());
                int biayaPembibitanInt = Integer.parseInt(biayaPembibitan.getText().toString());
                int biayaPerawatanInt = Integer.parseInt(biayaPerawatan.getText().toString());
                int biayaOverheadInt = Integer.parseInt(biayaOverhead.getText().toString());
                int biayaUtilitasInt = Integer.parseInt(biayaUtilitas.getText().toString());
                int biayaDistribusiPemasaranInt = Integer.parseInt(biayaDistribusiPemasaran.getText().toString());
                int totalBiaya = biayaPakanInt + biayaTenagaKerjaInt + biayaPembibitanInt + biayaPerawatanInt + biayaOverheadInt
                        + biayaUtilitasInt + biayaDistribusiPemasaranInt;

                if (totalBiaya <= 0) {
                    Toast.makeText(getBaseContext(), "Total Biaya Kosong" , Toast.LENGTH_SHORT ).show();
                }

                int jumlahPanenInt = Integer.parseInt(jumlahPanen.getText().toString());
                int hargaJualProdukInt = Integer.parseInt(hargaJualProduk.getText().toString());
                int totalKeuntungan = jumlahPanenInt * hargaJualProdukInt;

                double hariBEP = (totalBiaya * 1.0 / totalKeuntungan * 1.0 ) * (dayDifference * 1.0);


                hasil.setText(String.format("%.1f%n Hari", hariBEP));

             }
        });

    };
    public class clickHandler implements View.OnClickListener {
        EditText view;

        @Override
        public void onClick(View v) {
            view = (EditText) findViewById(v.getId());
            view.setText("");
        }
    }
}