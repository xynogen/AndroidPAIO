package com.polsri.paio.dataClass;

public class SettingPendapatan {
    String jumlahPanen, hargaJualProduk;

    public SettingPendapatan() {
    }

    public SettingPendapatan(String jumlahPanen, String hargaJualProduk) {
        this.jumlahPanen = jumlahPanen;
        this.hargaJualProduk = hargaJualProduk;
    }

    public String getJumlahPanen() {
        return jumlahPanen;
    }

    public void setJumlahPanen(String jumlahPanen) {
        this.jumlahPanen = jumlahPanen;
    }

    public String getHargaJualProduk() {
        return hargaJualProduk;
    }

    public void setHargaJualProduk(String hargaJualProduk) {
        this.hargaJualProduk = hargaJualProduk;
    }
}
