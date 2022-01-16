package com.epamtc.airline.entity.dto;

import java.io.Serializable;
import java.sql.Time;
import java.util.Objects;

public class RouteDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private String number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
                && Objects.equals(number, routeDto.number)
                && Objects.equals(duration, routeDto.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, number, departureID, destinationID, distance, duration);
    }

    @Override
    public String toString() {
        return "RouteDto{" +
                "ID=" + ID +
                ", number='" + number + '\'' +
                ", departureID=" + departureID +
                ", destinationID=" + destinationID +
                ", distance=" + distance +
                ", duration=" + duration +
                '}';
    }
}
