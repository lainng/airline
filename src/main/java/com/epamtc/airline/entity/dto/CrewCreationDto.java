package com.epamtc.airline.entity.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class CrewCreationDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private long assignedFlightID;
    private long[] members;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getAssignedFlightID() {
        return assignedFlightID;
    }

    public void setAssignedFlightID(long assignedFlightID) {
        this.assignedFlightID = assignedFlightID;
    }

    public long[] getMembers() {
        return members;
    }

    public void setMembers(long[] members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewCreationDto that = (CrewCreationDto) o;
        return ID == that.ID
                && assignedFlightID == that.assignedFlightID
                && Arrays.equals(members, that.members);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ID, assignedFlightID);
        result = 31 * result + Arrays.hashCode(members);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append('@');
        builder.append("ID=").append(ID);
        builder.append(", assignedFlightID=").append(assignedFlightID);
        builder.append(", members=").append(Arrays.toString(members));

        return builder.toString();
    }
}
