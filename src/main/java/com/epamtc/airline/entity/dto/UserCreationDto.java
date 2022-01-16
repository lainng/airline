package com.epamtc.airline.entity.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserCreationDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private String firstName;
    private String lastName;
    private long positionID;
    private String email;
    private String password;
    private String passwordConfirmation;

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

    public long getPositionID() {
        return positionID;
    }

    public void setPositionID(long positionID) {
        this.positionID = positionID;
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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreationDto dto = (UserCreationDto) o;
        return ID == dto.ID
                && positionID == dto.positionID
                && Objects.equals(firstName, dto.firstName)
                && Objects.equals(lastName, dto.lastName)
                && Objects.equals(email, dto.email)
                && Objects.equals(password, dto.password)
                && Objects.equals(passwordConfirmation, dto.passwordConfirmation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, firstName, lastName, positionID, email, password, passwordConfirmation);
    }

    @Override
    public String toString() {
        return "UserCreationDto{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", positionID=" + positionID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirmation='" + passwordConfirmation + '\'' +
                '}';
    }
}
