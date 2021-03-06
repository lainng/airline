package com.epamtc.airline.entity.dto;

import java.io.Serializable;
import java.sql.Time;
import java.util.Objects;

/**
 * This class represents the route DTO from the database.
 */
public class RouteDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private long departureID;
    private long destinationID;
    private int distance;
    private Time duration;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getDepartureID() {
        return departureID;
    }

    public void setDepartureID(long departureID) {
        this.departureID = departureID;
    }

    public long getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(long destinationID) {
        this.destinationID = destinationID;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteDto routeDto = (RouteDto) o;
        return ID == routeDto.ID
                && departureID == routeDto.departureID
                && destinationID == routeDto.destinationID
                && distance == routeDto.distance
                && Objects.equals(duration, routeDto.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, departureID, destinationID, distance, duration);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append('@');
        builder.append("ID=").append(ID);
        builder.append(", departureID=").append(departureID);
        builder.append(", destinationID=").append(destinationID);
        builder.append(", distance=").append(distance);
        builder.append(", duration=").append(duration);

        return builder.toString();
    }
}
