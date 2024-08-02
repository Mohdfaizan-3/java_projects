package com.uber.uberApp.services;

import com.uber.uberApp.dto.RideRequestDTO;
import com.uber.uberApp.entities.Ride;
import com.uber.uberApp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideByID(Long rideId);

    void matchWithDrivers(RideRequestDTO rideRequestDTO);

    Ride createNewRide(RideRequestDTO rideRequestDTO);

    Ride updateRideStatus(Long rideId, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);
}
