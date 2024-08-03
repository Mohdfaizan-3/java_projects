package com.uber.uberApp.strategies;

import com.uber.uberApp.dto.RideRequestDTO;

/**
 * Strategy interface for calculating ride fares.
 * This interface defines the contract for algorithms that compute the fare
 * for a ride based on various factors such as distance, time, demand, and
 * any applicable promotions or surcharges.
 */
public interface RideFareCalculationStrategy {

    /**
     * Calculates the fare for a ride based on the given ride request details.
     */
    double calculateFare(RideRequestDTO rideRequestDTO);
}