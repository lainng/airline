package com.epamtc.airline.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private String firstName;
    private String lastName;
    private Position position;
    private String email;
    private String password;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ID == user.ID
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName) &&
                Objects.equals(position, user.position) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, firstName, lastName, position, email, password);
    }

    @Override
    public String toString() {
        //TODO Stringbuilder!
        return getClass().getName() + "@" +
                "personnelNumber = " + ID +
                ", firstName = " + firstName +
                ", lastName = " + lastName +
                ", position = " + position +
                ", login = " + email +
                ", password = " + password;
    }
}
