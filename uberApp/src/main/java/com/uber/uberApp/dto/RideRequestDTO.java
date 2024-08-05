package com.uber.uberApp.dto;

import com.uber.uberApp.entities.enums.PaymentMethod;
import com.uber.uberApp.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing a Ride Request in the UberApp system.
 * This class encapsulates all relevant information about a ride request, including
 * locations, timing, rider details, payment method, and current status.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDTO {

    /**
     * Unique identifier for the ride request.
     */
    private Long id;

    /**
     * Geographic point representing the pickup location.
     */
    private PointDTO pickupLocation;

    /**
     * Geographic point representing the drop-off location.
     */
    private PointDTO dropOffLocation;

    /**
     * Timestamp when the ride was requested.
     */
    private LocalDateTime requestedTime;

    /**
     * Details of the rider making the request.
     */
    private RiderDTO rider;

    /**
     * Chosen method of payment for the ride.
     */
    private PaymentMethod paymentMethod;

    /**
     * Current status of the ride request.
     */
    private RideRequestStatus rideRequestStatus;
}