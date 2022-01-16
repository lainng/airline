package com.epamtc.airline.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
    public String toString() {
        return "Flight{" +
                "ID=" + ID +
                ", route=" + route +
                ", plane=" + plane +
                ", departureTime=" + departureTime +
                ", destinationTime=" + destinationTime +
                ", flightStatus=" + flightStatus +
                ", isConfirmed=" + isConfirmed +
                '}';
    }
}
