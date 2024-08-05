package com.uber.uberApp.services.impl;

import com.uber.uberApp.dto.DriverDTO;
import com.uber.uberApp.dto.RideDTO;
import com.uber.uberApp.dto.RideRequestDTO;
import com.uber.uberApp.dto.RiderDTO;
import com.uber.uberApp.entities.RideRequest;
import com.uber.uberApp.entities.Rider;
import com.uber.uberApp.entities.User;
import com.uber.uberApp.entities.enums.RideRequestStatus;
import com.uber.uberApp.repository.RideRequestRepository;
import com.uber.uberApp.repository.RiderRepository;
import com.uber.uberApp.services.RiderService;
import com.uber.uberApp.strategies.DriverMatchingStrategy;
import com.uber.uberApp.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the RiderService interface.
 * This service handles operations related to riders, including ride requests, cancellations,
 * driver ratings, and retrieval of rider-specific information.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideFareCalculationStrategy rideFareCalculationStrategy;
    private final DriverMatchingStrategy driverMatchingStrategy;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    @Override
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        RideRequest rideRequest = modelMapper.map(rideRequestDTO, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        double calculatedFare = rideFareCalculationStrategy.calculateFare(rideRequest);
        rideRequest.setFare(calculatedFare);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        driverMatchingStrategy.findMatchedDriver(rideRequest);
        return modelMapper.map(savedRideRequest, RideRequestDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        // TODO: Implement ride cancellation logic
        return null;
    }

    @Override
    public DriverDTO rateDriver(Long rideId, Integer rating) {
        // TODO: Implement driver rating logic
        return null;
    }

    @Override
    public RiderDTO getRiderProfile() {
        // TODO: Implement rider profile retrieval logic
        return null;
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder().user(user).rating(0.0).build();
        return riderRepository.save(rider);
    }

    @Override
    public List<RideDTO> getAllRides() {
        // TODO: Implement logic to retrieve all rides for the rider
        return List.of();
    }
}