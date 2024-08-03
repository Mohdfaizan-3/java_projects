package com.uber.uberApp.strategies.impl;

import com.uber.uberApp.dto.RideRequestDTO;
import com.uber.uberApp.entities.Driver;
import com.uber.uberApp.strategies.DriverMatchingStrategy;

import java.util.List;

/**
 * Implementation of DriverMatchingStrategy that matches ride requests with the highest-rated available drivers.
 * This strategy prioritizes driver quality based on ratings when finding suitable drivers for a ride request.
 */
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {

    @Override
    public List<Driver> findMatchedDriver(RideRequestDTO requestDTO) {
        // TODO: Implement the logic to find and sort drivers based on their ratings

        return List.of();
    }
}