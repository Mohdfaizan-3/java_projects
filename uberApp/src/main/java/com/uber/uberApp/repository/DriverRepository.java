package com.uber.uberApp.repository;

import com.uber.uberApp.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    double DEFAULT_SEARCH_RADIUS = 5000.0; // 5 km
    int DEFAULT_DRIVER_LIMIT = 10;

    @Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance " +
            "FROM drivers d " +
            "WHERE d.available = true " +
            "AND ST_DWithin(d.current_location, :pickupLocation, :radius) " +
            "ORDER BY distance " +
            "LIMIT :limit",
            nativeQuery = true)
    List<Driver> findNearbyAvailableDrivers(Point pickupLocation, double radius, int limit);

    default List<Driver> findNearbyAvailableDrivers(Point pickupLocation) {
        return findNearbyAvailableDrivers(pickupLocation, DEFAULT_SEARCH_RADIUS, DEFAULT_DRIVER_LIMIT);
    }

    Optional<Driver> findByUserId(Long userId);

    List<Driver> findByRatingGreaterThanEqual(Double rating);

    @Query("UPDATE Driver d SET d.available = :available WHERE d.id = :driverId")
    int updateDriverAvailability(Long driverId, boolean available);
}