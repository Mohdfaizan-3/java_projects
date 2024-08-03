package com.uber.uberApp.strategies;

import com.uber.uberApp.dto.RideRequestDTO;
import com.uber.uberApp.entities.Driver;

import java.util.List;

/**
 * Strategy interface for matching drivers to ride requests.
 * This interface defines the contract for algorithms that find suitable drivers
 * for a given ride request based on various criteria such as location, availability,
 * and rider preferences.
 */
public interface DriverMatchingStrategy {

    /**
     * Finds and returns a list of matched drivers for a given ride request.
     *
     * @param requestDTO The DTO containing details of the ride request, including
     *                   pickup location, drop-off location, and any specific requirements.
     * @return A list of Driver entities that match the criteria for the given ride request.
     *         The list may be empty if no suitable drivers are found.
     */
    List<Driver> findMatchedDriver(RideRequestDTO requestDTO);
}