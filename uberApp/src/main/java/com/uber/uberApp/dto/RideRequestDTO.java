package com.uber.uberApp.dto;

import com.uber.uberApp.entities.Rider;
import com.uber.uberApp.entities.enums.Payment;
import com.uber.uberApp.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDTO {

    private Long id;

    private Point pickupLocation;

    private Point dropOffLocation;

    private LocalDateTime requestedTime;

    private RiderDTO rider;

    private Payment paymentMethod;

    private RideRequestStatus rideRequestStatus;
}
