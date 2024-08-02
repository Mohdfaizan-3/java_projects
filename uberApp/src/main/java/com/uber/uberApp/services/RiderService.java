package com.uber.uberApp.services;

import com.uber.uberApp.dto.DriverDTO;
import com.uber.uberApp.dto.RideDTO;
import com.uber.uberApp.dto.RideRequestDTO;
import com.uber.uberApp.dto.RiderDTO;
import com.uber.uberApp.entities.RideRequest;

import java.util.List;

public interface RiderService {

    RideRequestDTO requestRide(RideRequestDTO rideRequestDTO);

    RideDTO cancelRide(Long rideId);

    DriverDTO rateDriver(Long rideId, Integer rating);

    RiderDTO getRiderProfile();

    List<RideDTO> getAllRides();
}
