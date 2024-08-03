package com.uber.uberApp.services.impl;

import com.uber.uberApp.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

/**
 * Implementation of the DistanceService interface.
 * This service provides functionality to calculate the distance between two geographical points.
 */
@Service
public class DistanceServiceImpl implements DistanceService {

    @Override
    public double calculateDistance(Point src, Point des) {
        // TODO: Implement distance calculation logic
        return 0;
    }
}