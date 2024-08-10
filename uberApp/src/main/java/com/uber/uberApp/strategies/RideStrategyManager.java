package com.uber.uberApp.strategies;

import com.uber.uberApp.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.uber.uberApp.strategies.impl.DriverMatchingNearestDriverStrategyImpl;
import com.uber.uberApp.strategies.impl.RideFareSurgePriceCalcStrategy;
import com.uber.uberApp.strategies.impl.RiderFareDefaultFareCalcStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingHighestRatedDriverStrategy driverMatchingHighestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategyImpl driverMatchingNearestDriverStrategy;
    private final RideFareSurgePriceCalcStrategy rideFareSurgePriceCalcStrategy;
    private final RiderFareDefaultFareCalcStrategy riderFareDefaultFareCalcStrategy;


    public DriverMatchingStrategy driverMatchingStrategy(Double rating) {
        if (rating >= 4) {
            return driverMatchingHighestRatedDriverStrategy;
        } else {
            return driverMatchingNearestDriverStrategy;
        }
    }


    public RideFareCalculationStrategy rideFareCalculationStrategy() {

        LocalTime startSurgeTime = LocalTime.of(20, 0);
        LocalTime endSurgeTime = LocalTime.of(23, 0);
        LocalTime currentTime = LocalTime.now();

        Boolean isSurgeTime = currentTime.isAfter(startSurgeTime) && currentTime.isBefore(endSurgeTime);

        if (isSurgeTime) return rideFareSurgePriceCalcStrategy;
        else return riderFareDefaultFareCalcStrategy;
    }
}
