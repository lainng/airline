package com.epamtc.airline.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a Flight Status entity.
 */
public class FlightStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private String name;

    public FlightStatus() {}

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightStatus that = (FlightStatus) o;
        return ID == that.ID
                && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append('@');
        builder.append("ID=").append(ID);
        builder.append(", name=").append(name);

        return builder.toString();
    }
}
