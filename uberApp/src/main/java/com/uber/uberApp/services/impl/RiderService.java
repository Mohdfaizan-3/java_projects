package com.uber.uberApp.services.impl;

import com.uber.uberApp.dto.RideRequestDTO;
import com.uber.uberApp.entities.Ride;
import com.uber.uberApp.entities.enums.RideStatus;
import com.uber.uberApp.services.RideService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class RiderServiceImpl implements RideService {
    @Override
    public Ride getRideByID(Long rideId) {
        return null;
    }

    @Override
    public void matchWithDrivers(RideRequestDTO rideRequestDTO) {

    }

    @Override
    public Ride createNewRide(RideRequestDTO rideRequestDTO) {
        return null;
    }

    @Override
    public Ride updateRideStatus(Long rideId, RideStatus rideStatus) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest) {
        return null;
    }
}
