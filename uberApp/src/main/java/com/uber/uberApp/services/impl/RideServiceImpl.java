package com.uber.uberApp.services.impl;

import com.uber.uberApp.dto.RideRequestDTO;
import com.uber.uberApp.entities.Driver;
import com.uber.uberApp.entities.Ride;
import com.uber.uberApp.entities.RideRequest;
import com.uber.uberApp.entities.enums.RideRequestStatus;
import com.uber.uberApp.entities.enums.RideStatus;
import com.uber.uberApp.exceptions.ResourceNotFoundException;
import com.uber.uberApp.repository.RideRepository;
import com.uber.uberApp.services.RideRequestService;
import com.uber.uberApp.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * Implementation of the RideService interface.
 * This service handles operations related to rides, including creation, retrieval,
 * status updates, and matching with drivers.
 */
@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(() -> new ResourceNotFoundException("driver with id not found"));
    }

    @Override
    public void matchWithDrivers(RideRequestDTO rideRequestDTO) {
        // TODO: Implement driver matching logic
    }


    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        Ride ride = modelMapper.map(rideRequest, Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateOtp());
        ride.setId(null);

        rideRequestService.update(rideRequest);
        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest) {
        // TODO: Implement rider's rides retrieval logic
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest) {
        // TODO: Implement driver's rides retrieval logic
        return null;
    }

    private String generateOtp() {
        SecureRandom secureRandom = new SecureRandom();
        int otp = secureRandom.nextInt(10000);
        return String.format("%04d", otp);
    }

}