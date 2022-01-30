package com.epamtc.airline.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * This class represents a Flight entity.
 */
public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private Route route;
    private Plane plane;
    private Timestamp departureTime;
    private Timestamp destinationTime;
    private FlightStatus flightStatus;
    private boolean isConfirmed;

    public Flight() {}

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Timestamp getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(Timestamp destinationTime) {
        this.destinationTime = destinationTime;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return ID == flight.ID
                && isConfirmed == flight.isConfirmed
                && Objects.equals(route, flight.route)
                && Objects.equals(plane, flight.plane)
                && Objects.equals(departureTime, flight.departureTime)
                && Objects.equals(destinationTime, flight.destinationTime)
                && Objects.equals(flightStatus, flight.flightStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, route, plane, departureTime, destinationTime, flightStatus, isConfirmed);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append('@');
        builder.append("ID=").append(ID);
        builder.append(", route=").append(route);
        builder.append(", plane=").append(plane);
        builder.append(", departureTime=").append(departureTime);
        builder.append(", destinationTime=").append(destinationTime);
        builder.append(", flightStatus=").append(flightStatus);
        builder.append(", isConfirmed=").append(isConfirmed);

        return builder.toString();
    }
}
