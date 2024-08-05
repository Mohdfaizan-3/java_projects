package com.uber.uberApp.strategies.impl;

import com.uber.uberApp.dto.RideRequestDTO;
import com.uber.uberApp.entities.RideRequest;
import com.uber.uberApp.services.DistanceService;
import com.uber.uberApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of RideFareCalculationStrategy that calculates ride fares
 * using a default pricing model without considering surge pricing or other
 * dynamic factors.
 * This strategy provides a baseline fare calculation for rides.
 */

@Service
@RequiredArgsConstructor
public class RiderFareDefaultFareCalcStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;
    @Override
    public double calculateFare(RideRequest rideRequest) {
        double calculatedDistance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());
        return calculatedDistance * FARE_MULTIPLIER;
    }
}