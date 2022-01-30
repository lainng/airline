package com.epamtc.airline.entity.dto;

import com.epamtc.airline.entity.User;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * This class represents the crew DTO from the database.
 */
public class CrewDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private long assignedFlightID;
    private List<User> members;

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

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewDto crewDto = (CrewDto) o;
        return ID == crewDto.ID
                && assignedFlightID == crewDto.assignedFlightID
                && Objects.equals(members, crewDto.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, assignedFlightID, members);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append('@');
        builder.append("ID=").append(ID);
        builder.append(", assignedFlightID=").append(assignedFlightID);
        builder.append(", members=").append(members);

        return builder.toString();
    }
}
