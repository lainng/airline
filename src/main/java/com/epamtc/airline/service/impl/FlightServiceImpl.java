package com.epamtc.airline.service.impl;

import com.epamtc.airline.command.FlightCondition;
import com.epamtc.airline.command.UserRole;
import com.epamtc.airline.dao.DaoFactory;
import com.epamtc.airline.dao.FlightDao;
import com.epamtc.airline.dao.FlightStatusDao;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.*;
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
import java.util.*;

public class FlightServiceImpl implements FlightService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int TWENTY_THREE = 23;
    private static final int FIFTY_NINE = 59;

    @Override
    public List<Flight> takeUserFlights(User user) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        List<Flight> flights;
        try {
            List<FlightDto> flightDtoList = flightDao.findUserFlights(user.getID());
            flights = toEntityList(flightDtoList);
        } catch (DaoException e) {
            LOGGER.error("Unable to get the list of user's flights. {}", e.getMessage());
            throw new ServiceException("Unable to get the list of user's flights.", e);
        }
        return flights;
    }

    @Override
    public boolean confirmFlight(long flightID, User user) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        if (!isFlightAssignedEmployee(user, flightID)) {
            return false;
        }
        try {
            flightDao.confirmFlightByID(flightID, user.getID());
        } catch (DaoException e) {
            LOGGER.error("Unable to confirm user flight. {}", e.getMessage());
            throw new ServiceException("Unable to confirm user flight.", e);
        }
        return true;
    }

    @Override
    public Optional<Flight> takeUserFlight(long flightID, User user) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        Optional<Flight> optionalFlight = Optional.empty();
        if (!isFlightAssignedEmployee(user, flightID)) {
            return optionalFlight;
        }
        try {
            FlightDto flightDto = flightDao.findFlightByID(flightID);
            optionalFlight = Optional.ofNullable(toEntity(flightDto));
        } catch (DaoException e) {
            LOGGER.error("Unable to get the flight by ID. {}", e.getMessage());
            throw new ServiceException("Unable to get the flight by ID.", e);
        }
        return optionalFlight;
    }

    @Override
    public List<Flight> takeUnassignedFlights() throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        List<Flight> flights;
        try {
            List<FlightDto> flightDtoList = flightDao.findUnassignedFlights();
            if (!flightDtoList.isEmpty()) {
                flightDtoList.removeIf(flightDto -> flightDto.getFlightStatus().getID() == FlightCondition.CANCELED);
            }
            flights = toEntityList(flightDtoList);
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
        List<Flight> flights;
        try {
            List<FlightDto> flightDtoList = flightDao.findAllFlights();
            flights = toEntityList(flightDtoList);
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
            if (isFlightCanceledOrNotExists(flightID)) {
                return false;
            }
            flightDao.cancelFlightByID(flightID);
        } catch (DaoException e) {
            LOGGER.error("Unable to cancel the flight. {}", e.getMessage());
            throw new ServiceException("Unable to cancel the flight.", e);
        }
        return true;
    }

    @Override
    public long createFlight(FlightDto flightDto) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        FlightStatus scheduled = takeFlightStatus(FlightCondition.SCHEDULED);
        try {
            flightDto.setFlightStatus(scheduled);
            return flightDao.addFlight(flightDto);
        } catch (DaoException e) {
            LOGGER.error("Unable to create a new flight. {}", e.getMessage());
            throw new ServiceException("Unable to create a new flight.", e);
        }
    }

    @Override
    public void editFlight(FlightDto flightDto) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        try {
            flightDao.updateFlight(flightDto);
        } catch (DaoException e) {
            LOGGER.error("Unable to update the flight. {}", e.getMessage());
            throw new ServiceException("Unable to update the flight.", e);
        }
    }

    @Override
    public List<Flight> searchFlights(SearchQuery query) throws ServiceException {
        FlightDao flightDao = DaoFactory.getInstance().getFlightDao();
        List<Flight> searchResult;
        changeQueryTimeRightBorder(query);
        try {
            List<FlightDto> flightDtoList = flightDao.findFlightsBySearchQuery(query);
            searchResult = toEntityList(flightDtoList);
        } catch (DaoException e) {
            LOGGER.error("Unable to get a search result. {}", e.getMessage());
            throw new ServiceException("Unable to get a search result.", e);
        }
        return searchResult;
    }

    @Override
    public FlightStatus takeFlightStatus(long statusID) throws ServiceException {
        FlightStatusDao flightStatusDao = DaoFactory.getInstance().getFlightStatusDao();
        FlightStatus status;
        try {
            status = flightStatusDao.findFlightStatusByID(statusID);
        } catch (DaoException e) {
            LOGGER.error("Unable to get a flight status. {}", e.getMessage());
            throw new ServiceException("Unable to get a a flight status.", e);
        }
        return status;
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

        updateFlightStatus(flight);

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

    private void changeQueryTimeRightBorder(SearchQuery query) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(query.getDestDate().getTime());
        calendar.set(Calendar.HOUR, TWENTY_THREE);
        calendar.set(Calendar.MINUTE, FIFTY_NINE);
        calendar.set(Calendar.SECOND, FIFTY_NINE);
        query.setDestDate(new Timestamp(calendar.getTimeInMillis()));
    }

    private void updateFlightStatus(Flight flight) throws ServiceException {
        long flightStatusID = flight.getFlightStatus().getID();
        if (flightStatusID == FlightCondition.CANCELED 
                || flightStatusID == FlightCondition.ARRIVED) {
            return;
        }

        Date now = new Date();
        if (flightStatusID == FlightCondition.SCHEDULED
                && now.after(flight.getDepartureTime())) {
            editFlightStatus(flight, FlightCondition.CANCELED);
        } else if (now.after(flight.getDestinationTime())) {
            editFlightStatus(flight, FlightCondition.ARRIVED);
            calculateTotalFlightHours(flight);
        } else if (now.after(flight.getDepartureTime())) {
            editFlightStatus(flight, FlightCondition.DEPARTED);
        }
    }
    
    private void calculateTotalFlightHours(Flight flight) throws ServiceException {
        PlaneService planeService = ServiceFactory.getInstance().getPlaneService();
        Plane plane = flight.getPlane();

        Calendar onWayHours = Calendar.getInstance();
        onWayHours.setTimeInMillis(flight.getRoute().getDuration().getTime());

        int hoursOnWay = onWayHours.get(Calendar.HOUR_OF_DAY);
        int totalHours = plane.getFlyingHours();
        totalHours += hoursOnWay;

        plane.setFlyingHours(totalHours);
        planeService.editPlane(plane);
    }

    private List<Flight> toEntityList(List<FlightDto> dtoList) throws ServiceException {
        List<Flight> flights = new ArrayList<>(dtoList.size());
        for (FlightDto dto : dtoList) {
            Flight flight = toEntity(dto);
            flights.add(flight);
        }
        return flights;
    }

    private void editFlightStatus(Flight flight, long statusID) throws ServiceException {
        flight.setFlightStatus(takeFlightStatus(statusID));
        changeFlightStatus(flight.getID(), statusID);
    }

    private boolean isFlightCanceledOrNotExists(long flightID) throws ServiceException {
        Optional<Flight> optionalFlight = takeFlight(flightID);
        if (!optionalFlight.isPresent()) {
            return true;
        }
        Flight flight = optionalFlight.get();

        return flight.getFlightStatus().getID() == FlightCondition.CANCELED;
    }
}