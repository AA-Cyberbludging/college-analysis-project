package com.cyberbludging.cap.entity;

public class udata {
    private Integer uid;
    private Double sex_ratio;
    private Double employ_rate;
    private Double shipment_rate;
    private Double enrollment_rate;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getSex_ratio() {
        return sex_ratio;
    }

    public void setSex_ratio(Double sex_ratio) {
        this.sex_ratio = sex_ratio;
    }

    public Double getEmploy_rate() {
        return employ_rate;
    }

    public void setEmploy_rate(Double employ_rate) {
        this.employ_rate = employ_rate;
    }

    public Double getShipment_rate() {
        return shipment_rate;
    }

    public void setShipment_rate(Double shipment_rate) {
        this.shipment_rate = shipment_rate;
    }

    public Double getEnrollment_rate() {
        return enrollment_rate;
    }

    public void setEnrollment_rate(Double enrollment_rate) {
        this.enrollment_rate = enrollment_rate;
    }

    @Override
    public String toString() {
        return "udata{" +
                "uid=" + uid +
                ", sex_ratio=" + sex_ratio +
                ", employ_rate=" + employ_rate +
                ", shipment_rate=" + shipment_rate +
                ", enrollment_rate=" + enrollment_rate +
                '}';
    }
}
