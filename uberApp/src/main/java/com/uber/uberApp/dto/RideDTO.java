package com.uber.uberApp.dto;

import com.uber.uberApp.entities.enums.PaymentMethod;
import com.uber.uberApp.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing a Ride in the UberApp system.
 * This class encapsulates all relevant information about a ride, including
 * locations, timings, participants, payment details, and status.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDTO {

    private Long id;

    private PointDTO pickupLocation;

    private PointDTO dropOffLocation;

    private LocalDateTime createdTime;

    private RiderDTO rider;

    private DriverDTO driver;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private Double fare;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String otp;
}