package com.uber.uberApp.strategies.impl;

import com.uber.uberApp.dto.RideRequestDTO;
import com.uber.uberApp.entities.Driver;
import com.uber.uberApp.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingNearestDriverStrategyImpl implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchedDriver(RideRequestDTO requestDTO) {
        return List.of();
    }
}
