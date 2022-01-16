package com.epamtc.airline.entity.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class SearchQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    private Timestamp deptDate;
    private Timestamp destDate;
    private long departmentID;
    private long destinationID;

    public Timestamp getDeptDate() {
        return deptDate;
    }

    public void setDeptDate(Timestamp deptDate) {
        this.deptDate = deptDate;
    }

    public Timestamp getDestDate() {
        return destDate;
    }

    public void setDestDate(Timestamp destDate) {
        this.destDate = destDate;
    }

    public long getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(long departmentID) {
        this.departmentID = departmentID;
    }

    public long getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(long destinationID) {
        this.destinationID = destinationID;
    }

    @Override
    public String toString() {
        return "SearchQuery{" +
                "deptDate=" + deptDate +
                ", destDate=" + destDate +
                ", departmentID=" + departmentID +
                ", destinationID=" + destinationID +
                '}';
    }
}
