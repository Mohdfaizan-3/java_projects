package com.uber.uberApp.entities;

import com.uber.uberApp.entities.enums.Payment;
import com.uber.uberApp.entities.enums.RideRequestStatus;
import com.uber.uberApp.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "GEOGRAPHY(Point, 4326)")
    private Point pickupLocation;

    @Column(columnDefinition = "GEOGRAPHY(Point, 4326)")
    private Point dropOffLocation;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;

    @OneToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private Payment paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    private Double fare;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
