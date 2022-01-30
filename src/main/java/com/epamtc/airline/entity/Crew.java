package com.epamtc.airline.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a Crew entity.
 */
public class Crew implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private Flight assignedFlight;
    private List<User> members;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Flight getAssignedFlight() {
        return assignedFlight;
    }

    public void setAssignedFlight(Flight assignedFlight) {
        this.assignedFlight = assignedFlight;
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
        Crew crew = (Crew) o;
        return ID == crew.ID
                && Objects.equals(assignedFlight, crew.assignedFlight)
                && Objects.equals(members, crew.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, assignedFlight, members);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append('@');
        builder.append("ID=").append(ID);
        builder.append(", assignedFlight=").append(assignedFlight);
        builder.append(", members=").append(members.toString());

        return builder.toString();
    }
}
