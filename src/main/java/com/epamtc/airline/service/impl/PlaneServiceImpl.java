package com.epamtc.airline.service.impl;

import com.epamtc.airline.dao.DaoFactory;
import com.epamtc.airline.dao.PlaneDao;
import com.epamtc.airline.dao.exception.DaoException;
import com.epamtc.airline.entity.Plane;
import com.epamtc.airline.service.PlaneService;
import com.epamtc.airline.service.exception.ServiceException;
import com.epamtc.airline.service.validation.PlaneValidator;
import com.epamtc.airline.service.validation.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class PlaneServiceImpl implements PlaneService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<Plane> takePlane(long planeID) throws ServiceException {
        PlaneDao planeDao = DaoFactory.getInstance().getPlaneDao();
        Plane plane;
        try {
            plane = planeDao.takePlaneByID(planeID);
        } catch (DaoException e) {
            LOGGER.error("Unable to get the plane by its ID. {}", e.getMessage());
            throw new ServiceException("Unable to get the plane by its ID.", e);
        }
        return Optional.ofNullable(plane);
    }

    @Override
    public List<Plane> takeAllPlanes() throws ServiceException {
        PlaneDao planeDao = DaoFactory.getInstance().getPlaneDao();
        List<Plane> planes;
        try {
            planes = planeDao.takeAllPlanes();
        } catch (DaoException e) {
            LOGGER.error("Unable to get all planes. {}", e.getMessage());
            throw new ServiceException("Unable to get all planes.", e);
        }
        return planes;
    }

    @Override
    public boolean createPlane(Plane plane) throws ServiceException {
        PlaneValidator validator = ValidatorFactory.getInstance().getPlaneValidator();
        if (!validator.validatePlaneEntity(plane)) {
            return false;
        }
        PlaneDao planeDao = DaoFactory.getInstance().getPlaneDao();
        try {
            planeDao.insertPlane(plane);
        } catch (DaoException e) {
            LOGGER.error("Unable to add a new plane. {}", e.getMessage());
            throw new ServiceException("Unable to add a new plane.", e);
        }
        return true;
    }

    @Override
    public boolean editPlane(Plane plane) throws ServiceException {
        PlaneValidator validator = ValidatorFactory.getInstance().getPlaneValidator();
        if (!validator.validatePlaneEntity(plane)) {
            return false;
        }
        PlaneDao planeDao = DaoFactory.getInstance().getPlaneDao();
        try {
            planeDao.updatePlane(plane);
        } catch (DaoException e) {
            LOGGER.error("Unable to edit a plane. {}", e.getMessage());
            throw new ServiceException("Unable to edit a plane.", e);
        }
        return true;
    }
}