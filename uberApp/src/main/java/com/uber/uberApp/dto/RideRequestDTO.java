package com.uber.uberApp.dto;

import com.uber.uberApp.entities.enums.PaymentMethod;
import com.uber.uberApp.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing a Ride Request in the UberApp system.
 * This class encapsulates all relevant information about a ride request, including
 * locations, timing, rider details, payment method, and current status.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDTO {


    private Long id;

    private PointDTO pickupLocation;

    private PointDTO dropOffLocation;

    private PaymentMethod paymentMethod;

    private LocalDateTime requestedTime;

    private RiderDTO rider;

    private Double fare;

    private RideRequestStatus rideRequestStatus;
}