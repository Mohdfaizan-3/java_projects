package com.uber.uberApp.strategies;

import com.uber.uberApp.entities.Driver;
import com.uber.uberApp.entities.RideRequest;

import java.util.List;

/**
 * Strategy interface for matching drivers to ride requests.
 * This interface defines the contract for algorithms that find suitable drivers
 * for a given ride request based on various criteria such as location, availability,
 * and rider preferences.
 */
public interface DriverMatchingStrategy {

    List<Driver> findMatchedDriver(RideRequest rideRequest);
}