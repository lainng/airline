package com.epamtc.airline.service.impl;

import com.epamtc.airline.command.UserRole;
import com.epamtc.airline.dao.DaoFactory;
import com.epamtc.airline.dao.FlightDao;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.entity.Plane;
import com.epamtc.airline.entity.Route;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.FlightDto;
import com.epamtc.airline.entity.dto.SearchQuery;
import com.epamtc.airline.service.FlightService;
import com.epamtc.airline.service.PlaneService;
import com.epamtc.airline.service.RouteService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class FlightServiceImpl implements FlightService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<Flight> takeUserFlights(User user) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        List<Flight> flights = new ArrayList<>();
        try {
            List<FlightDto> flightDtoList = flightDao.findUserFlights(user.getID());
            for (FlightDto dto : flightDtoList) {
                flights.add(toEntity(dto));
            }
        } catch (DaoException e) {
            LOGGER.error("Unable to get the list of user's flights. {}", e.getMessage());
            throw new ServiceException(e);
        }
        return flights;
    }
    @Override
    public boolean confirmFlight(long flightID, User user) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        try {
            if (!isFlightAssignedEmployee(user, flightID)) {
                return false;
            }
            flightDao.confirmFlightByID(flightID, user.getID());
        } catch (DaoException e) {
            LOGGER.error("Unable to confirm user flight. {}", e.getMessage());
            throw new ServiceException(e);
        }
        return true;
    }
    @Override
    public Optional<Flight> takeUserFlight(long flightID, User user) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        Flight flight = null;
        try {
            if(isFlightAssignedEmployee(user, flightID)) {
                FlightDto flightDto = flightDao.findFlightByID(flightID);
                flight = toEntity(flightDto);
            }
        } catch (DaoException e) {
            LOGGER.error("Unable to get the flight by ID. {}", e.getMessage());
            throw new ServiceException("Unable to get the flight by ID.", e);
        }
        return Optional.ofNullable(flight);
    }
    @Override
    public List<Flight> takeUnassignedFlights() throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        List<Flight> flights = new ArrayList<>();
        try {
            List<FlightDto> flightDtoList = flightDao.findUnassignedFlights();
            for (FlightDto dto : flightDtoList) {
                flights.add(toEntity(dto));
            }
        } catch (DaoException e) {
            LOGGER.error("Unable to get the list of unassigned flights. {}", e.getMessage());
            throw new ServiceException("Unable to get the list of unassigned flights.", e);
        }
        return flights;
    }
    @Override
    public Optional<Flight> takeFlight(long flightID) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        Flight flight;
        try {
            FlightDto flightDto = flightDao.findFlightByID(flightID);
            flight = toEntity(flightDto);
        } catch (DaoException e) {
            LOGGER.error("Unable to get the flight by specified ID. {}", e.getMessage());
            throw new ServiceException("Unable to get the flight by specified ID.", e);
        }
        return Optional.ofNullable(flight);
    }
    @Override
    public List<Flight> takeAllFlights() throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        List<Flight> flights = new ArrayList<>();
        try {
            List<FlightDto> flightDtoList = flightDao.findAllFlights();
            for (FlightDto dto : flightDtoList) {
                flights.add(toEntity(dto));
            }
        } catch (DaoException e) {
            LOGGER.error("Unable to get all flights. {}", e.getMessage());
            throw new ServiceException("Unable to get all flights.", e);
        }
        return flights;
    }
    @Override
    public void changeFlightStatus(long flightID, long statusID) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        try {
            flightDao.updateFlightStatus(flightID, statusID);
        } catch (DaoException e) {
            LOGGER.error("Unable to change flight status. {}", e.getMessage());
            throw new ServiceException("Unable to change flight status.", e);
        }
    }
    @Override
    public boolean cancelFlight(long flightID) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        try {
            Optional<Flight> flight = takeFlight(flightID);
            if (flight.isPresent()) {
                flightDao.cancelFlightByID(flightID);
            } else {
                return false;
            }
        } catch (DaoException e) {
            LOGGER.error("Unable to cancel the flight. {}", e.getMessage());
            throw new ServiceException("Unable to cancel the flight.", e);
        }
        return true;
    }
    @Override
    public boolean createFlight(FlightDto flightDto) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        try {
            flightDao.insertFlight(flightDto);
        } catch (DaoException e) {
            LOGGER.error("Unable to create a new flight. {}", e.getMessage());
            throw new ServiceException("Unable to create a new flight.", e);
        }
        return true;
    }
    @Override
    public boolean editFlight(FlightDto flightDto) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        try {
            flightDao.updateFlight(flightDto);
        } catch (DaoException e) {
            LOGGER.error("Unable to update the flight. {}", e.getMessage());
            throw new ServiceException("Unable to update the flight.", e);
        }
        return true;
    }
    @Override
    public List<Flight> searchFlights(SearchQuery query) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        List<Flight> searchResult = new ArrayList<>();
        try {
            List<FlightDto> flightDtoList = flightDao.takeSearchResults(query);
            for (FlightDto dto : flightDtoList) {
                searchResult.add(toEntity(dto));
            }
        } catch (DaoException e) {
            LOGGER.error("Unable to get a search result. {}", e.getMessage());
            throw new ServiceException("Unable to get a search result.", e);
        }
        return searchResult;
    }

    private Flight toEntity(FlightDto dto) throws ServiceException {
        if (dto == null) {
            return null;
        }
        PlaneService planeService = ServiceFactory.getInstance().getPlaneService();
        RouteService routeService = ServiceFactory.getInstance().getRouteService();
        Flight flight = new Flight();

        flight.setID(dto.getID());
        Optional<Route> optionalRoute = routeService.takeRoute(dto.getRouteID());
        optionalRoute.ifPresent(flight::setRoute);
        Optional<Plane> optionalPlane = planeService.takePlane(dto.getPlaneID());
        optionalPlane.ifPresent(flight::setPlane);
        flight.setDepartureTime(dto.getDepartureTime());
        calculateDestinationTime(flight);
        flight.setFlightStatus(dto.getFlightStatus());
        flight.setConfirmed(dto.isConfirmed());

        return flight;
    }
    private boolean isFlightAssignedEmployee(User user, long flightID) throws ServiceException {
        if (user.getPosition().getRoleID() != UserRole.USER) {
            return true;
        }
        List<Flight> userFlights = takeUserFlights(user);
        for (Flight flight : userFlights) {
            if (flight.getID() == flightID) {
                return true;
            }
        }
        return false;
    }
    private void calculateDestinationTime(Flight flight) {
        Calendar destinationTime = Calendar.getInstance();
        Calendar duration = Calendar.getInstance();
        duration.setTime(flight.getRoute().getDuration());
        destinationTime.setTime(flight.getDepartureTime());
        destinationTime.add(Calendar.HOUR, duration.get(Calendar.HOUR));
        destinationTime.add(Calendar.MINUTE, duration.get(Calendar.MINUTE));
        flight.setDestinationTime(new Timestamp(destinationTime.getTimeInMillis()));
    }
}