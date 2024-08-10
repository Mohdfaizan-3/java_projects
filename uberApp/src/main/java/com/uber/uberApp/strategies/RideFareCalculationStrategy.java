package com.uber.uberApp.strategies;

import com.uber.uberApp.entities.RideRequest;

/**
 * Strategy interface for calculating ride fares.
 * This interface defines the contract for algorithms that compute the fare
 * for a ride based on various factors such as distance, time, demand, and
 * any applicable promotions or surcharges.
 */
public interface RideFareCalculationStrategy {

    Double FARE_MULTIPLIER = 15.0;

    Double calculateFare(RideRequest rideRequest);
}