package com.epamtc.airline.entity;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private String title;
    private long roleID;

    public Position() {}

    public Position(long ID, String title, long roleID) {
        this.ID = ID;
        this.title = title;
        this.roleID = roleID;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getRoleID() {
        return roleID;
    }

    public void setRoleID(long roleID) {
        this.roleID = roleID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position that = (Position) o;
        return ID == that.ID
                && roleID == that.roleID
                && title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, title, roleID);
    }

    @Override
    public String toString() {
        //TODO Stringbuilder!
        return "Position{" +
                "ID=" + ID +
                ", position='" + title + '\'' +
                ", roleID=" + roleID +
                '}';
    }
}
