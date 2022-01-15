package com.polsri.paio.dataClass;

public class Snap {
    public String trig, rotasi;

    public Snap(){}

    public Snap(String trig, String rotasi) {
        this.trig = trig;
        this.rotasi = rotasi;
    }

    public String getTrig() {
        return trig;
    }

    public void setTrig(String trig) {
        this.trig = trig;
    }

    public String getRotasi() {
        return rotasi;
    }

    public void setRotasi(String rotasi) {
        this.rotasi = rotasi;
    }
}
