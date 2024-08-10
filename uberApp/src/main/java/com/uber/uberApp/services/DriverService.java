package com.uber.uberApp.services;

import com.uber.uberApp.dto.DriverDTO;
import com.uber.uberApp.dto.RideDTO;
import com.uber.uberApp.entities.Driver;

import java.util.List;

public interface DriverService {

    RideDTO acceptRide(Long rideRequestId);

    Driver getCurrentDriver();

    RideDTO cancelRide(Long rideId);

    RideDTO startRide(Long rideId, String otp);

    RideDTO endRide(Long rideId);

    RideDTO rateRider(Long rideId, Integer rating);

    DriverDTO getDriverProfile();

    List<RideDTO> getAllRides();

}
