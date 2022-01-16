package com.epamtc.airline.entity.dto;

import com.epamtc.airline.entity.FlightStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class FlightDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private long routeID;
    private long planeID;
    private Timestamp departureTime;
    private Timestamp destinationTime;
    private FlightStatus flightStatus;
    private boolean isConfirmed;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getRouteID() {
        return routeID;
    }

    public void setRouteID(long routeID) {
        this.routeID = routeID;
    }

    public long getPlaneID() {
        return planeID;
    }

    public void setPlaneID(long planeID) {
        this.planeID = planeID;
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
        return "FlightDto{" +
                "ID=" + ID +
                ", routeID=" + routeID +
                ", planeID=" + planeID +
                ", departureTime=" + departureTime +
                ", destinationTime=" + destinationTime +
                ", flightStatus=" + flightStatus +
                ", isConfirmed=" + isConfirmed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightDto flightDto = (FlightDto) o;
        return ID == flightDto.ID
                && routeID == flightDto.routeID
                && planeID == flightDto.planeID
                && isConfirmed == flightDto.isConfirmed
                && Objects.equals(departureTime, flightDto.departureTime)
                && Objects.equals(destinationTime, flightDto.destinationTime)
                && Objects.equals(flightStatus, flightDto.flightStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, routeID, planeID, departureTime, destinationTime, flightStatus, isConfirmed);
    }
}
