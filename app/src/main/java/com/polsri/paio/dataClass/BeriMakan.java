package com.polsri.paio.dataClass;

public class BeriMakan {
    public String beratPakan, timerPakan, beratSekarang;

    public BeriMakan() {}

    public BeriMakan(String beratPakan, String timerPakan, String beratSekarang) {
        this.beratPakan = beratPakan;
        this.timerPakan = timerPakan;
        this.beratSekarang = beratSekarang;
    }

    public String getBeratPakan() {
        return beratPakan;
    }

    public void setBeratPakan(String beratPakan) {
        this.beratPakan = beratPakan;
    }

    public String getTimerPakan() {
        return timerPakan;
    }

    public void setTimerPakan(String timerPakan) {
        this.timerPakan = timerPakan;
    }

    public String getBeratSekarang() {
        return beratSekarang;
    }

    public void setBeratSekarang(String beratSekarang) {
        this.beratSekarang = beratSekarang;
    }
}
