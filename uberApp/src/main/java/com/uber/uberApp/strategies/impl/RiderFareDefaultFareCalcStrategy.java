package com.uber.uberApp.strategies.impl;

import com.uber.uberApp.entities.RideRequest;
import com.uber.uberApp.services.DistanceService;
import com.uber.uberApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of RideFareCalculationStrategy that calculates ride fares
 * using a default pricing model without considering surge pricing or other
 * dynamic factors.
 * This strategy provides a baseline fare calculation for rides.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RiderFareDefaultFareCalcStrategy implements RideFareCalculationStrategy {

    private static final Double FARE_MULTIPLIER = 10.0; // Example value, adjust as needed
    private static final Double BASE_FARE = 50.0; // Example base fare
    private static final Double MINIMUM_FARE = 10.0; // Minimum fare for any ride

    private final DistanceService distanceService;

    @Override
    public Double calculateFare(RideRequest rideRequest) {
        if (rideRequest == null || rideRequest.getPickupLocation() == null || rideRequest.getDropOffLocation() == null) {
            log.error("Invalid ride request or locations");
            throw new IllegalArgumentException("Invalid ride request or locations");
        }

        Double calculatedDistance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());
        log.info("Calculated distance for ride: {} km", calculatedDistance);
        if (calculatedDistance == 0.0) {
            Double fare = BASE_FARE;
            return fare;
        }
        Double fare = BASE_FARE + (calculatedDistance * FARE_MULTIPLIER);
        fare = Math.max(fare, MINIMUM_FARE);

        log.info("Calculated fare for ride: ${}", String.format("%.2f", fare));
        return fare;
    }
}