package com.uber.uberApp.entities.enums;

/**
 * Represents the various states a ride request can be in within the UberApp system.
 */
public enum RideRequestStatus {
    /**
     * The ride request has been submitted but not yet accepted or confirmed.
     */
    PENDING,

    /**
     * The ride request was cancelled by the rider or the system before confirmation.
     */
    CANCELLED,

    /**
     * The ride request has been confirmed and is ready to proceed to the ride stage.
     */
    CONFIRMED
}