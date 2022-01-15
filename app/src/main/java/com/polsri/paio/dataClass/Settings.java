package com.polsri.paio.dataClass;

public class Settings {
    public String restart, panjang, lebar, jarakKeDasar;

    public Settings(String restart, String panjang, String lebar, String jarakKeDasar) {
        this.restart = restart;
        this.panjang = panjang;
        this.lebar = lebar;
        this.jarakKeDasar = jarakKeDasar;
    }
    public Settings() {
    }

    public String getRestart() {
        return restart;
    }

    public void setRestart(String restart) {
        this.restart = restart;
    }

    public String getPanjang() {
        return panjang;
    }

    public void setPanjang(String panjang) {
        this.panjang = panjang;
    }

    public String getLebar() {
        return lebar;
    }

    public void setLebar(String lebar) {
        this.lebar = lebar;
    }

    public String getJarakKeDasar() {
        return jarakKeDasar;
    }

    public void setJarakKeDasar(String jarakKeDasar) {
        this.jarakKeDasar = jarakKeDasar;
    }
}
