package com.epamtc.airline.command;

public final class FlightCondition {
    public static final long SCHEDULED = 1L;
    public static final long READY = 2L;
    public static final long DEPARTED = 3L;
    public static final long ARRIVED = 4L;
    public static final long CANCELED = 5L;

    private FlightCondition() {}
}
