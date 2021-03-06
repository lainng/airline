package com.epamtc.airline.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a Plane entity.
 */
public class Plane implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private String model;
    private int flyingHours;
    private int passengerCapacity;
    private int flightRange;

    public Plane() {}

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getFlyingHours() {
        return flyingHours;
    }

    public void setFlyingHours(int flyingHours) {
        this.flyingHours = flyingHours;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public int getFlightRange() {
        return flightRange;
    }

    public void setFlightRange(int flightRange) {
        this.flightRange = flightRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return ID == plane.ID
                && flyingHours == plane.flyingHours
                && passengerCapacity == plane.passengerCapacity
                && flightRange == plane.flightRange
                && Objects.equals(model, plane.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, model, flyingHours, passengerCapacity, flightRange);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append('@');
        builder.append("ID=").append(ID);
        builder.append(", model=").append(model);
        builder.append(", flyingHours=").append(flyingHours);
        builder.append(", passengerCapacity=").append(passengerCapacity);
        builder.append(", flightRange=").append(flightRange);

        return builder.toString();
    }
}
