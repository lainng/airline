package com.epamtc.airline.entity;

import java.io.Serializable;

public class FlightStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private String designation;

    public FlightStatus() {}

    public FlightStatus(long ID) {
        this.ID = ID;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "FlightStatus{" +
                "ID=" + ID +
                ", designation='" + designation + '\'' +
                '}';
    }
}
