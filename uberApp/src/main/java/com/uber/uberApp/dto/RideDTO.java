package com.uber.uberApp.dto;

import com.uber.uberApp.entities.enums.PaymentMethod;
import com.uber.uberApp.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing a Ride in the UberApp system.
 * This class encapsulates all relevant information about a ride, including
 * locations, timings, participants, payment details, and status.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RideDTO {

    /**
     * Unique identifier for the ride.
     */
    private Long id;

    /**
     * Geographic point representing the pickup location.
     */
    private Point pickupLocation;

    /**
     * Geographic point representing the drop-off location.
     */
    private Point dropOffLocation;

    /**
     * Timestamp when the ride was created/requested.
     */
    private LocalDateTime createdTime;

    /**
     * Details of the rider requesting the ride.
     */
    private RiderDTO rider;

    /**
     * Details of the driver assigned to the ride.
     */
    private DriverDTO driver;

    /**
     * Method of payment for the ride.
     */
    private PaymentMethod paymentMethod;

    /**
     * Current status of the ride.
     */
    private RideStatus rideStatus;

    /**
     * Total fare for the ride.
     */
    private Double fare;

    /**
     * Timestamp when the ride started.
     */
    private LocalDateTime startTime;

    /**
     * Timestamp when the ride ended.
     */
    private LocalDateTime endTime;

    private String otp;
}