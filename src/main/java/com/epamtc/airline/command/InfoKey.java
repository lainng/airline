package com.epamtc.airline.command;

public final class InfoKey {
    public static final String ERROR_INVALID_SEARCH_QUERY_PARAMETERS = "error.incorrectSearchingQueryParameters";

    public static final String ERROR_INCORRECT_LOGIN_DATA = "error.invalidLogin";
    public static final String ERROR_INCORRECT_SIGN_UP_DATA = ""; /*todo написать ключ */
    public static final String ERROR_NOT_REGISTERED = "error.userExists"; /*todo написать ключ - проверьте правильность введенных данных */

    public static final String ERROR_INCORRECT_PASSWORD = "error.incorrectPassword";
    public static final String PASSWORD_CHANGED = "success.changingPassword";

    public static final String NEW_CREW = "crewAction.newCrew";
    public static final String EDIT_CREW = "crewAction.editCrew";

    public static final String NEW_ROUTE = "routeAction.newRoute";
    public static final String EDIT_ROUTE = "routeAction.editRoute";

    public static final String NEW_PLANE = "planeAction.newPlane";
    public static final String EDIT_PLANE = "planeAction.editPlane";

    public static final String NEW_FLIGHT = "flightAction.newFlight";
    public static final String EDIT_FLIGHT = "flightAction.editFlight";

    public static final String SUCCESS_ADDED_PLANE = "success.newPlane";
    public static final String SUCCESS_UPDATED_PLANE = "success.editPlane";
    public static final String ERROR_INCORRECT_PLANE_PARAMETERS = "error.incorrectPlaneParameters";

    public static final String SUCCESS_ADDED_CREW = "success.newCrew";
    public static final String SUCCESS_UPDATED_CREW = "success.updateCrew";
    public static final String SUCCESS_DELETED_CREW = "success.deletingCrew";
    public static final String ERROR_NO_SUCH_CREW = "error.noSuchCrew";
    public static final String ERROR_INCORRECT_CREW_PARAMETERS = "error.incorrectCrewParameters";
    public static final String NOT_ASSIGNED_CREW = "flightInfo.crew.notAssigned";

    public static final String SUCCESS_ADDED_CITY = "success.newCity";
    public static final String SUCCESS_UPDATED_CITY = "success.updateCity";
    public static final String ERROR_INCORRECT_CITY_PARAMETERS = "error.incorrectCityParameters";


    public static final String SUCCESS_ADDED_ROUTE = "success.newRoute";
    public static final String SUCCESS_UPDATED_ROUTE = "success.updateRoute";
    public static final String ERROR_INCORRECT_ROUTE_PARAMETERS = "error.incorrectRouteParameters";

    public static final String SUCCESS_UPDATED_EMPLOYEE = "success.updateEmployee";
    public static final String ERROR_INCORRECT_EMPLOYEE_DATA = ""; /*todo написать ключ */

    public static final String SUCCESS_CANCELED_FLIGHT = "success.cancelFlight";
    public static final String SUCCESS_ADDED_FLIGHT = "success.newFlight";
    public static final String SUCCESS_UPDATED_FLIGHT = "success.updateFlight";
    public static final String ERROR_INCORRECT_FLIGHT_PARAMETERS = "error.incorrectFlightParameters";
    public static final String ERROR_NO_SUCH_FLIGHT = "error.noSuchFlight";

}
