package com.epamtc.airline.command.factory;

import com.epamtc.airline.command.Command;
import com.epamtc.airline.command.CommandName;
import com.epamtc.airline.command.factory.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides the entities of implementations of the command interface.
 */
public class CommandFactory {
    private final Map<String, Command> commandMap = new HashMap<>();

    private CommandFactory() {
        commandMap.put(CommandName.LOGIN_COMMAND, new LoginCommand());
        commandMap.put(CommandName.LOGIN_PAGE_COMMAND, new LoginPageCommand());
        commandMap.put(CommandName.SIGN_UP_COMMAND, new SignUpCommand());
        commandMap.put(CommandName.HOME_PAGE_COMMAND, new HomePageCommand());
        commandMap.put(CommandName.SIGN_UP_PAGE_COMMAND, new SignUpPageCommand());
        commandMap.put(CommandName.USER_PAGE_COMMAND, new UserPageCommand());
        commandMap.put(CommandName.CONFIRM_FLIGHT_COMMAND, new ConfirmFlightCommand());
        commandMap.put(CommandName.FLIGHT_INFO_COMMAND, new FlightInfoCommand());
        commandMap.put(CommandName.LOGOUT_COMMAND, new LogoutCommand());
        commandMap.put(CommandName.REGISTERED_PAGE_COMMAND, new RegisteredPageCommand());
        commandMap.put(CommandName.CONTACTS_COMMAND, new ContactsCommand());
        commandMap.put(CommandName.ABOUT_COMPANY_COMMAND, new AboutCompanyCommand());
        commandMap.put(CommandName.SETTINGS_COMMAND, new SettingsPageCommand());
        commandMap.put(CommandName.CHANGE_PASSWORD_COMMAND, new ChangePasswordCommand());
        commandMap.put(CommandName.DISPATCHER_PAGE_COMMAND, new DispatcherPageCommand());
        commandMap.put(CommandName.DISPATCHER_CREWS_PAGE_COMMAND, new DispatcherCrewsPageCommand());
        commandMap.put(CommandName.DISPATCHER_FLIGHTS_PAGE_COMMAND, new DispatcherFlightsPageCommand());
        commandMap.put(CommandName.DISPATCHER_STAFF_PAGE_COMMAND, new DispatcherStaffPageCommand());
        commandMap.put(CommandName.CREW_ACTION_PAGE_COMMAND, new CrewActionPageCommand());
        commandMap.put(CommandName.ADD_CREW_COMMAND, new AddCrewCommand());
        commandMap.put(CommandName.DELETE_CREW_COMMAND, new DeleteCrewCommand());
        commandMap.put(CommandName.CREWS_PAGE_COMMAND, new CrewsPageCommand());
        commandMap.put(CommandName.ADMIN_PAGE_COMMAND, new AdminPageCommand());
        commandMap.put(CommandName.STAFF_PAGE_COMMAND, new StaffPageCommand());
        commandMap.put(CommandName.STAFF_ACTION_PAGE_COMMAND, new StaffActionPageCommand());
        commandMap.put(CommandName.ROUTES_PAGE_COMMAND, new RoutesPageCommand());
        commandMap.put(CommandName.ROUTE_ACTION_PAGE_COMMAND, new RouteActionPageCommand());
        commandMap.put(CommandName.PLANES_PAGE_COMMAND, new PlanesPageCommand());
        commandMap.put(CommandName.PLANE_ACTION_PAGE_COMMAND, new PlaneActionPageCommand());
        commandMap.put(CommandName.FLIGHTS_PAGE_COMMAND, new FlightsPageCommand());
        commandMap.put(CommandName.FLIGHT_ACTION_PAGE_COMMAND, new FlightActionPageCommand());
        commandMap.put(CommandName.ADD_PLANE_COMMAND, new AddPlaneCommand());
        commandMap.put(CommandName.CITIES_PAGE_COMMAND, new CitiesPageCommand());
        commandMap.put(CommandName.CITY_ACTION_PAGE_COMMAND, new CityActionPageCommand());
        commandMap.put(CommandName.ADD_CITY_COMMAND, new AddCityCommand());
        commandMap.put(CommandName.ADD_ROUTE_COMMAND, new AddRouteCommand());
        commandMap.put(CommandName.EDIT_EMPLOYEE_COMMAND, new EditEmployeeCommand());
        commandMap.put(CommandName.CANCEL_FLIGHT_COMMAND, new CancelFlightCommand());
        commandMap.put(CommandName.ADD_FLIGHT_COMMAND, new AddFlightCommand());
        commandMap.put(CommandName.SEARCHING_FLIGHT_COMMAND, new SearchingFlightCommand());
    }

    public static CommandFactory getInstance() {
        return CommandFactoryHolder.INSTANCE;
    }

    public Command takeCommand(String commandName) {
        return commandMap.get(commandName);
    }

    private static class CommandFactoryHolder {
        static final CommandFactory INSTANCE = new CommandFactory();
    }

}