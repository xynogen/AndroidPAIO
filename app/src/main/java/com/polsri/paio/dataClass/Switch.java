package com.polsri.paio.dataClass;

public class Switch {

    public String blower, pompa;

    public Switch() {
    }

    public Switch(String blower, String pompa) {
        this.blower = blower;
        this.pompa = pompa;
    }

    public void setBlower(String blower) {
        this.blower = blower;
    }

    public void setPompa(String pompa) {
        this.pompa = pompa;
    }

    public String getBlower() {
        return blower;
    }

    public String getPompa() {
        return pompa;
    }
}
