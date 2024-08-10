package com.uber.uberApp.services.impl;

import com.uber.uberApp.dto.DriverDTO;
import com.uber.uberApp.dto.RideDTO;
import com.uber.uberApp.entities.Driver;
import com.uber.uberApp.entities.Ride;
import com.uber.uberApp.entities.RideRequest;
import com.uber.uberApp.entities.enums.RideRequestStatus;
import com.uber.uberApp.entities.enums.RideStatus;
import com.uber.uberApp.exceptions.ResourceNotFoundException;
import com.uber.uberApp.exceptions.RunTimeConflictException;
import com.uber.uberApp.repository.DriverRepository;
import com.uber.uberApp.services.DriverService;
import com.uber.uberApp.services.RideRequestService;
import com.uber.uberApp.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of the DriverService interface.
 * This service handles operations related to drivers, including ride management and profile retrieval.
 */
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    public RideDTO acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);

        if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
            throw new RuntimeException("ride request cannot be accepted as it is not pending");
        }

        Driver currentDriver = getCurrentDriver();
        if (!currentDriver.getAvailable()) {
            throw new RuntimeException("current cannot accept the ride");
        }

        currentDriver.setAvailable(false);
        Driver savedDriver = driverRepository.save(currentDriver);

        Ride ride = rideService.createNewRide(rideRequest, savedDriver);

        return modelMapper.map(ride, RideDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        // TODO: Implement ride cancellation logic
        return null;
    }

    @Override
    public RideDTO startRide(Long rideId, String otp) {

        Ride ride = rideService.getRideById(rideId);
        if (ride == null) {
            throw new ResourceNotFoundException("Ride not found");
        }

        Driver driver = getCurrentDriver();

        if (!driver.equals(ride.getDriver())) {
            throw new RunTimeConflictException("You are not authorized to start this ride");
        }

        if (!RideStatus.CONFIRMED.equals(ride.getRideStatus())) {
            throw new RunTimeConflictException("Ride is not in CONFIRMED status");
        }

        if (otp == null ||otp.isEmpty()  || otp.trim().isEmpty() ) {
            throw new RunTimeConflictException("OTP is required");
        }

        if (!otp.equals(ride.getOtp())) {
            throw new RunTimeConflictException("Invalid Otp");
        }

        ride.setStartTime(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);

        return modelMapper.map(savedRide, RideDTO.class);
    }

    @Override
    public RideDTO endRide(Long rideId) {
        // TODO: Implement ride end logic
        return null;
    }

    @Override
    public RideDTO rateRider(Long rideId, Integer rating) {
        // TODO: Implement rider rating logic
        return null;
    }

    @Override
    public DriverDTO getDriverProfile() {
        // TODO: Implement driver profile retrieval logic
        return null;
    }

    @Override
    public List<RideDTO> getAllRides() {
        // TODO: Implement logic to retrieve all rides for the driver
        return List.of();
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("diver not found"));

    }

}