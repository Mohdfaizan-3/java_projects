package com.uber.uberApp.strategies.impl;

import com.uber.uberApp.dto.RideRequestDTO;
import com.uber.uberApp.strategies.RideFareCalculationStrategy;

/**
 * Implementation of RideFareCalculationStrategy that calculates ride fares
 * using a default pricing model without considering surge pricing or other
 * dynamic factors.
 * This strategy provides a baseline fare calculation for rides.
 */
public class RiderFareDefaultFareCalcStrategy implements RideFareCalculationStrategy {

    @Override
    public double calculateFare(RideRequestDTO rideRequestDTO) {
        // TODO: Implement default fare calculation logic
        return 0;
    }
}