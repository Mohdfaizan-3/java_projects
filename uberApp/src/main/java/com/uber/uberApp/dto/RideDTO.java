package com.uber.uberApp.dto;


import com.uber.uberApp.entities.enums.Payment;
import com.uber.uberApp.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RideDTO {


    private Long id;

    private Point pickupLocation;

    private Point dropOffLocation;

    private LocalDateTime createdTime;

    private RiderDTO rider;
    private DriverDTO driver;

    private Payment paymentMethod;

    private RideStatus rideStatus;

    private Double fare;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
