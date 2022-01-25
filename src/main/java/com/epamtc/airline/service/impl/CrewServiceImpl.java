package com.epamtc.airline.service.impl;

import com.epamtc.airline.command.FlightCondition;
import com.epamtc.airline.dao.CrewDao;
import com.epamtc.airline.dao.DaoFactory;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.Crew;
import com.epamtc.airline.entity.Flight;
import com.epamtc.airline.entity.User;
import com.epamtc.airline.entity.dto.CrewCreationDto;
import com.epamtc.airline.entity.dto.CrewDto;
import com.epamtc.airline.service.CrewService;
import com.epamtc.airline.service.FlightService;
import com.epamtc.airline.service.ServiceFactory;
import com.epamtc.airline.service.UserService;
import com.epamtc.airline.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrewServiceImpl implements CrewService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<Crew> takeCrewByFlightID(long flightID) throws ServiceException {
        CrewDao crewDao = DaoFactory.getInstance().getCrewDao();
        Crew crew;
        try {
            CrewDto crewDto = crewDao.findCrewByFlightID(flightID);
            crew = toEntity(crewDto);
        } catch (DaoException e) {
            LOGGER.error("Unable to take crew. {}", e.getMessage());
            throw new ServiceException("Unable to take crew.", e);
        }
        return Optional.ofNullable(crew);
    }

    @Override
    public List<Crew> takeAllCrews() throws ServiceException {
        CrewDao crewDao = DaoFactory.getInstance().getCrewDao();
        List<Crew> crews = new ArrayList<>();
        try {
            List<CrewDto> crewDtoList = crewDao.findAllCrews();
            for (CrewDto dto : crewDtoList) {
                crews.add(toEntity(dto));
            }
        } catch (DaoException e) {
            LOGGER.error("Unable to take crews. {}", e.getMessage());
            throw new ServiceException("Unable to take crews.", e);
        }
        return crews;
    }

    @Override
    public void editCrew(CrewCreationDto crewCreationDto) throws ServiceException {
        CrewDao crewDao = DaoFactory.getInstance().getCrewDao();
        try {
            crewDao.updateCrew(crewCreationDto);
        } catch (DaoException e) {
            LOGGER.error("Unable to update crew information. {}", e.getMessage());
            throw new ServiceException("Unable to update crew information.", e);
        }
    }

    @Override
    public boolean createCrew(CrewCreationDto crewCreationDto) throws ServiceException {
        CrewDao crewDao = DaoFactory.getInstance().getCrewDao();
        FlightService flightService = ServiceFactory.getInstance().getFlightService();
        if (isFlightAssigned(crewCreationDto.getAssignedFlightID())) {
            return false;
        }
        try {
            crewDao.addCrew(crewCreationDto);
            flightService.changeFlightStatus(crewCreationDto.getAssignedFlightID(), FlightCondition.READY);
        } catch (DaoException e) {
            LOGGER.error("Unable to create a new crew. {}", e.getMessage());
            throw new ServiceException("Unable to create a new crew.", e);
        }
        return true;
    }

    @Override
    public boolean deleteCrew(long crewID) throws ServiceException {
        CrewDao crewDao = DaoFactory.getInstance().getCrewDao();
        FlightService flightService = ServiceFactory.getInstance().getFlightService();
        try {
            Optional<Crew> optionalCrew = takeCrewByID(crewID);
            if (!optionalCrew.isPresent()) {
                return false;
            }
            crewDao.deleteCrew(crewID);
            Flight assignedFlight = optionalCrew.get().getAssignedFlight();
            flightService.changeFlightStatus(assignedFlight.getID(), FlightCondition.SCHEDULED);
        } catch (DaoException e) {
            LOGGER.error("Unable to delete a crew. {}", e.getMessage());
            throw new ServiceException("Unable to delete a crew.", e);
        }
        return true;
    }

    @Override
    public Optional<Crew> takeCrewByID(long crewID) throws ServiceException {
        CrewDao crewDao = DaoFactory.getInstance().getCrewDao();
        Crew crew;
        try {
            CrewDto dto = crewDao.findCrewByID(crewID);
            crew = toEntity(dto);
        } catch (DaoException e) {
            LOGGER.error("Unable to take the crew by its ID. {}", e.getMessage());
            throw new ServiceException("Unable to take the crew by its ID.", e);
        }
        return Optional.ofNullable(crew);
    }

    @Override
    public List<Crew> takeUserCrews(long userID) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        Optional<User> optionalUser = userService.takeUser(userID);
        List<Crew> userCrews = new ArrayList<>();
        if (!optionalUser.isPresent()) {
            return userCrews;
        }
        List<Crew> allCrews = takeAllCrews();
        for (Crew crew : allCrews) {
            if (crew.getMembers().contains(optionalUser.get())) {
                userCrews.add(crew);
            }
        }
        return userCrews;
    }

    private Crew toEntity(CrewDto dto) throws ServiceException {
        if (dto == null) {
            return null;
        }
        Crew crew = new Crew();
        FlightService flightService = ServiceFactory.getInstance().getFlightService();

        crew.setID(dto.getID());
        crew.setMembers(dto.getMembers());
        Optional<Flight> optionalFlight = flightService.takeFlight(dto.getAssignedFlightID());
        optionalFlight.ifPresent(crew::setAssignedFlight);

        return crew;
    }

    private boolean isFlightAssigned(long currentFlightID) throws ServiceException {
        FlightService flightService = ServiceFactory.getInstance().getFlightService();
        List<Flight> flights = flightService.takeUnassignedFlights();
        for (Flight flight : flights) {
            if(currentFlightID == flight.getID()) {
                return false;
            }
        }
        return true;
    }
}