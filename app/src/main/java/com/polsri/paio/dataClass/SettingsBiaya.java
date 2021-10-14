package com.polsri.paio.dataClass;

public class SettingsBiaya {
    String biayaPakan, biayaTenagaKerja, biayaPembibitan, biayaPerawatan, biayaOverhead,
            biayaUtilitas, biayaDistribusiPemasaran;

    public SettingsBiaya() {}

    public SettingsBiaya(String biayaPakan, String biayaTenagaKerja, String biayaPembibitan,
                         String biayaPerawatan, String biayaOverhead, String biayaUtilitas,
                         String biayaDistribusiPemasaran) {
        this.biayaPakan = biayaPakan;
        this.biayaTenagaKerja = biayaTenagaKerja;
        this.biayaPembibitan = biayaPembibitan;
        this.biayaPerawatan = biayaPerawatan;
        this.biayaOverhead = biayaOverhead;
        this.biayaUtilitas = biayaUtilitas;
        this.biayaDistribusiPemasaran = biayaDistribusiPemasaran;
    }

    public String getBiayaPakan() {
        return biayaPakan;
    }

    public void setBiayaPakan(String biayaPakan) {
        this.biayaPakan = biayaPakan;
    }

    public String getBiayaTenagaKerja() {
        return biayaTenagaKerja;
    }

    public void setBiayaTenagaKerja(String biayaTenagaKerja) {
        this.biayaTenagaKerja = biayaTenagaKerja;
    }

    public String getBiayaPembibitan() {
        return biayaPembibitan;
    }

    public void setBiayaPembibitan(String biayaPembibitan) {
        this.biayaPembibitan = biayaPembibitan;
    }

    public String getBiayaPerawatan() {
        return biayaPerawatan;
    }

    public void setBiayaPerawatan(String biayaPerawatan) {
        this.biayaPerawatan = biayaPerawatan;
    }

    public String getBiayaOverhead() {
        return biayaOverhead;
    }

    public void setBiayaOverhead(String biayaOverhead) {
        this.biayaOverhead = biayaOverhead;
    }

    public String getBiayaUtilitas() {
        return biayaUtilitas;
    }

    public void setBiayaUtilitas(String biayaUtilitas) {
        this.biayaUtilitas = biayaUtilitas;
    }

    public String getBiayaDistribusiPemasaran() {
        return biayaDistribusiPemasaran;
    }

    public void setBiayaDistribusiPemasaran(String biayaDistribusiPemasaran) {
        this.biayaDistribusiPemasaran = biayaDistribusiPemasaran;
    }
}
