package com.epamtc.airline.command;

/**
 * This class represents the holder of the page path and request routing type.
 */
public class CommandResult {
    private final String pagePath;
    private final RouteType type;

    public CommandResult(String pagePath, RouteType type) {
        this.pagePath = pagePath;
        this.type = type;
    }

    public String getPagePath() {
        return pagePath;
    }

    public boolean isRedirect() {
        return type == RouteType.REDIRECT;
    }

    public boolean isForward() {
        return type == RouteType.FORWARD;
    }
}