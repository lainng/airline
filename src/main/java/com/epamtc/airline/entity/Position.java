package com.epamtc.airline.entity;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    private long ID;
    private String name;
    private long roleID;

    public Position() {}

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, roleID);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@");
        builder.append("ID=").append(ID);
        builder.append(", name=").append(name);
        builder.append(", roleID=").append(roleID);

        return builder.toString();
    }
}
