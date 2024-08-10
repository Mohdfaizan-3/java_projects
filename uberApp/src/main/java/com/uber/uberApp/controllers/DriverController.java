package com.uber.uberApp.controllers;

import com.uber.uberApp.advices.ApiResponse;
import com.uber.uberApp.dto.RideDTO;
import com.uber.uberApp.dto.RideStartDTO;
import com.uber.uberApp.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {

    public final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<ApiResponse<RideDTO>> acceptRide(@PathVariable Long rideRequestId) {
        RideDTO rideDTO = driverService.acceptRide(rideRequestId);
        return ResponseEntity.ok(new ApiResponse<>(rideDTO));
    }

    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<ApiResponse<RideDTO>> startRide(@PathVariable Long rideRequestId,
                                                          @RequestBody RideStartDTO rideStartDTO) {
        RideDTO rideDTO = driverService.startRide(rideRequestId, rideStartDTO.getOtp());
        return ResponseEntity.ok(new ApiResponse<>(rideDTO));
    }
}
