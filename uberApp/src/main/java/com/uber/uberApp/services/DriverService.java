package com.uber.uberApp.services;

import com.uber.uberApp.dto.DriverDTO;
import com.uber.uberApp.dto.RideDTO;
import com.uber.uberApp.dto.RiderDTO;

import java.util.List;

public interface DriverService {

    RideDTO acceptRide(Long rideId);

    RideDTO cancelRide(Long rideId);

    RideDTO startRide(Long rideId);

    RideDTO endRide(Long rideId);

    RideDTO rateRider(Long rideId, Integer rating);

    DriverDTO getDriverProfile();

    List<RideDTO> getAllRides();

}
