package com.uber.uberApp.services.impl;

import com.uber.uberApp.dto.DriverDTO;
import com.uber.uberApp.dto.RideDTO;
import com.uber.uberApp.dto.RideRequestDTO;
import com.uber.uberApp.dto.RiderDTO;
import com.uber.uberApp.entities.Driver;
import com.uber.uberApp.entities.RideRequest;
import com.uber.uberApp.entities.Rider;
import com.uber.uberApp.entities.User;
import com.uber.uberApp.entities.enums.RideRequestStatus;
import com.uber.uberApp.repository.RideRequestRepository;
import com.uber.uberApp.repository.RiderRepository;
import com.uber.uberApp.services.RiderService;
import com.uber.uberApp.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideStrategyManager rideStrategyManager;

    @Override
    @Transactional
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDTO, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        List<Driver> drivers = rideStrategyManager
                .driverMatchingStrategy(rider.getRating()).findMatchedDriver(rideRequest);

//        TODO : Send notification to all the drivers about this ride request

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

    @Override
    public Rider getCurrentRider() {
        return riderRepository.findById(1L).orElseThrow(() -> new RuntimeException("rider not found"));
    }
}