package com.polsri.paio.dataClass;

public class Sensor {

    public String ph;
    public String turbidity;
    public String temp;
    public String distance;

    public Sensor(){}

    public Sensor(String ph, String turbidity, String temp, String distance) {
        this.ph = ph;
        this.turbidity = turbidity;
        this.temp = temp;
        this.distance = distance;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public void setTurbidity(String turbidity) {
        this.turbidity = turbidity;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPH() {
        return ph;
    }

    public String getTurbidity() {
        return turbidity;
    }

    public String getTemp() {
        return temp;
    }

    public String getDistance() {
        return distance;
    }
}
