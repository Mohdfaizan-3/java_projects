package com.uber.uberApp.services.impl;

import com.uber.uberApp.services.DistanceService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;

/**
 * Implementation of the DistanceService interface.
 * This service provides functionality to calculate the distance between two geographical points.
 */
@Service
@Slf4j
public class DistanceServiceImpl implements DistanceService {

    private static final String OSRM_API = "http://router.project-osrm.org/route/v1/driving/";
    private static final Double METERS_TO_KM = 1000.0;

    @Override
    public Double calculateDistance(Point src, Point des) {
        String uri = String.format("%f,%f;%f,%f", src.getX(), src.getY(), des.getX(), des.getY());
        log.info("Calculating distance with URI: {}", OSRM_API + uri);

        try {
            OsrmResponseDTO osrmResponseDTO = RestClient.builder()
                    .baseUrl(OSRM_API)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OsrmResponseDTO.class);

            log.info("Received response: {}", osrmResponseDTO);

            if (osrmResponseDTO == null) {
                throw new RuntimeException("Null response received from OSRM API");
            }

            if (osrmResponseDTO.getRoutes() == null || osrmResponseDTO.getRoutes().isEmpty()) {
                throw new RuntimeException("No routes found in the OSRM API response");
            }

            OSRMRoute route = osrmResponseDTO.getRoutes().get(0);
            if (route == null) {
                throw new RuntimeException("First route in OSRM API response is null");
            }

            Double distanceInMeters = route.getDistance();
            log.info("Distance in meters: {}", distanceInMeters);

            Double distanceInKm = distanceInMeters / METERS_TO_KM;
            log.info("Distance in km: {}", distanceInKm);

            return distanceInKm;

        } catch (RestClientException e) {
            log.error("Error while calling OSRM API: ", e);
            throw new RuntimeException("Error while fetching OSRM data: " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("Unexpected error in calculateDistance: ", e);
            throw new RuntimeException("Unexpected error in calculateDistance: " + e.getMessage(), e);
        }
    }
}

@Data
class OsrmResponseDTO {
    private List<OSRMRoute> routes;
}

@Data
class OSRMRoute {
    private Double distance;
}