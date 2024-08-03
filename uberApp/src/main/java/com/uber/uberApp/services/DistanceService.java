package com.uber.uberApp.services;

import org.locationtech.jts.geom.Point;

/**
 * Service interface for calculating distances between geographical points.
 * This service provides functionality to compute the distance between two locations
 * represented as Point objects.
 */
public interface DistanceService {

    double calculateDistance(Point src, Point des);
}