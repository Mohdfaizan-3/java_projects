package com.uber.uberApp.strategies.impl;

import com.uber.uberApp.entities.Driver;
import com.uber.uberApp.entities.RideRequest;
import com.uber.uberApp.repository.DriverRepository;
import com.uber.uberApp.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of DriverMatchingStrategy that matches ride requests with the highest-rated available drivers.
 * This strategy prioritizes driver quality based on ratings when finding suitable drivers for a ride request.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchedDriver(RideRequest rideRequest) {
        List<Driver> topRatedDriver = driverRepository.findNearbyTopRatedDrivers(rideRequest.getPickupLocation());
        log.info("driver :{}", topRatedDriver);
        return topRatedDriver;
    }
}