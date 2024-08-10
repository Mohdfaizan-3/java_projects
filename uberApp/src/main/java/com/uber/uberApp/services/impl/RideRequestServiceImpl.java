package com.uber.uberApp.services.impl;

import com.uber.uberApp.entities.RideRequest;
import com.uber.uberApp.exceptions.ResourceNotFoundException;
import com.uber.uberApp.repository.RideRequestRepository;
import com.uber.uberApp.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("rideRequest id not found"));
    }

    @Override
    @Transactional
    public void update(RideRequest rideRequest) {
        rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("rideRequest not found"));
        rideRequestRepository.save(rideRequest);
    }
}
