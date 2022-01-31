package com.epamtc.airline.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a User entity.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private String firstName;
    private String lastName;
    private Position position;
    private String email;
    private String password;
    private boolean isConfirmedAssignedFlight;

    public User() {}

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConfirmedAssignedFlight() {
        return isConfirmedAssignedFlight;
    }

    public void setConfirmedAssignedFlight(boolean confirmedAssignedFlight) {
        isConfirmedAssignedFlight = confirmedAssignedFlight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ID == user.ID
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(position, user.position)
                && Objects.equals(email, user.email)
                && Objects.equals(password, user.password)
                && isConfirmedAssignedFlight == user.isConfirmedAssignedFlight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, firstName, lastName, position, email, password, isConfirmedAssignedFlight);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@");
        builder.append("ID = ").append(ID)
                .append(", firstName=").append(firstName)
                .append(", lastName=").append(lastName)
                .append(", position=").append(position)
                .append(", email=").append(email)
                .append(", password=").append(password)
                .append(" , isConfirmedAssignedFlight=").append(isConfirmedAssignedFlight);
        return builder.toString();
    }
}
