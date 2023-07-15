package com.cyberbludging.cap.entity;

public class University {
    private Integer uid;
    private String uname;
    private String pname;
    private String utype;
    private Integer upopularity;

    private Double sexRatio;
    private Double employRate;
    private Double shipmentRate;
    private Double enrollmentRate;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public Integer getUpopularity() {
        return upopularity;
    }

    public void setUpopularity(Integer upopularity) {
        this.upopularity = upopularity;
    }

    public Double getSexRatio() {
        return sexRatio;
    }

    public void setSexRatio(Double sexRatio) {
        this.sexRatio = sexRatio;
    }

    public Double getEmployRate() {
        return employRate;
    }

    public void setEmployRate(Double employRate) {
        this.employRate = employRate;
    }

    public Double getShipmentRate() {
        return shipmentRate;
    }

    public void setShipmentRate(Double shipmentRate) {
        this.shipmentRate = shipmentRate;
    }

    public Double getEnrollmentRate() {
        return enrollmentRate;
    }

    public void setEnrollmentRate(Double enrollmentRate) {
        this.enrollmentRate = enrollmentRate;
    }

    @Override
    public String toString() {
        return "University{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", pname='" + pname + '\'' +
                ", utype='" + utype + '\'' +
                ", upopularity=" + upopularity +
                ", sexRatio=" + sexRatio +
                ", employRate=" + employRate +
                ", shipmentRate=" + shipmentRate +
                ", enrollmentRate=" + enrollmentRate +
                '}';
    }
}


