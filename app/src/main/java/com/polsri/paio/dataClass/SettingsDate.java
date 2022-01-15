package com.polsri.paio.dataClass;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SettingsDate {
    public String tanggalPembudidayaan, tanggaTerjual;

    public SettingsDate() {}

    public SettingsDate(String tanggalPembudidayaan, String tanggaTerjual) {
        this.tanggalPembudidayaan = tanggalPembudidayaan;
        this.tanggaTerjual = tanggaTerjual;
    }

    public String getTanggalPembudidayaan() {
        return tanggalPembudidayaan;
    }

    public void setTanggalPembudidayaan(String tanggalPembudidayaan) {
        this.tanggalPembudidayaan = tanggalPembudidayaan;
    }

    public String getTanggaTerjual() {
        return tanggaTerjual;
    }

    public void setTanggaTerjual(String tanggaTerjual) {
        this.tanggaTerjual = tanggaTerjual;
    }


    public int getDayDifference() {
        Date datePembudidayaan, dateProduk;
        if (this.tanggalPembudidayaan.isEmpty() || this.tanggaTerjual.isEmpty()) {
            return 0;
        } else {
            try {
                datePembudidayaan = new SimpleDateFormat("d/M/yyyy").parse(getTanggalPembudidayaan());
                dateProduk = new SimpleDateFormat("d/M/yyyy").parse(getTanggaTerjual());
            }
            catch (Exception e) {
                return 0;
            }
        }

        long timeDiff = dateProduk.getTime() - datePembudidayaan.getTime();
        int dayDifference = (int) ((timeDiff / (1000*60*60*24)) % 365);
        return dayDifference;
    }
}
