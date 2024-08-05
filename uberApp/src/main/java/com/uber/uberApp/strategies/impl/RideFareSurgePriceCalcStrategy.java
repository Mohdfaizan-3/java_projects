package com.uber.uberApp.strategies.impl;

import com.uber.uberApp.dto.RideRequestDTO;
import com.uber.uberApp.entities.RideRequest;
import com.uber.uberApp.strategies.RideFareCalculationStrategy;

/**
 * Implementation of RideFareCalculationStrategy that calculates ride fares
 * incorporating surge pricing during high-demand periods.
 * This strategy adjusts the base fare based on current demand and supply conditions.
 */
public class RideFareSurgePriceCalcStrategy implements RideFareCalculationStrategy {


    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}