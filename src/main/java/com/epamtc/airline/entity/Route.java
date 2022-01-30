package com.epamtc.airline.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Objects;

/**
 * This class represents a Route entity.
 */
public class Route implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private String number;
    private City departure;
    private City destination;
    private int distance;
    private Time duration;

    public Route() {}

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

    public City getDeparture() {
        return departure;
    }

    public void setDeparture(City departure) {
        this.departure = departure;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
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
        Route route = (Route) o;
        return ID == route.ID
                && distance == route.distance
                && Objects.equals(number, route.number)
                && Objects.equals(departure, route.departure)
                && Objects.equals(destination, route.destination)
                && Objects.equals(duration, route.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, number, departure, destination, distance, duration);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append('@');
        builder.append("ID=").append(ID);
        builder.append(", departurePoint=").append(departure);
        builder.append(", destination=").append(destination);
        builder.append(", distance=").append(distance);
        builder.append(", duration=").append(duration);

        return builder.toString();
    }
}
