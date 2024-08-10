package com.uber.uberApp.strategies.impl;

import com.uber.uberApp.entities.RideRequest;
import com.uber.uberApp.services.DistanceService;
import com.uber.uberApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of RideFareCalculationStrategy that calculates ride fares
 * incorporating surge pricing during high-demand periods.
 * This strategy adjusts the base fare based on current demand and supply conditions.
 */

@Service
@RequiredArgsConstructor
public class RideFareSurgePriceCalcStrategy implements RideFareCalculationStrategy {

    private static final Double SURGE_FACTOR = 2.3;
    private final DistanceService distanceService;

    @Override
    public Double calculateFare(RideRequest rideRequest) {
        Double calculatedDistance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());
        return calculatedDistance * FARE_MULTIPLIER * SURGE_FACTOR;
    }
}