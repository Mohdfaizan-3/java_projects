package com.uber.uberApp.controllers;

import com.uber.uberApp.advices.ApiResponse;
import com.uber.uberApp.dto.RideRequestDTO;
import com.uber.uberApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
public class RiderController {

    private final RiderService riderService;

    @PostMapping("/requestRide")
    public ResponseEntity<ApiResponse<RideRequestDTO>> requestRide(@RequestBody RideRequestDTO rideRequestDTO) {
        RideRequestDTO createdRideRequest = riderService.requestRide(rideRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(createdRideRequest));
    }

}
