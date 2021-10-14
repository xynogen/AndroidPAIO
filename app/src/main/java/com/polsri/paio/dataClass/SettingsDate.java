package com.polsri.paio.dataClass;

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
}
