package com.uber.uberApp.services.impl;

import com.uber.uberApp.dto.DriverDTO;
import com.uber.uberApp.dto.RideDTO;
import com.uber.uberApp.dto.RideRequestDTO;
import com.uber.uberApp.dto.RiderDTO;
import com.uber.uberApp.services.RiderService;

import java.util.List;

public class RiderServiceImpl implements RiderService {
    @Override
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        return null;
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDTO rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDTO getRiderProfile() {
        return null;
    }

    @Override
    public List<RideDTO> getAllRides() {
        return List.of();
    }
}
