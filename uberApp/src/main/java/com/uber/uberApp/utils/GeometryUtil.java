package com.uber.uberApp.utils;

import com.uber.uberApp.dto.PointDTO;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

/**
 * Utility class for geometric operations.
 * Provides methods for creating Point objects from PointDTO objects.
 */
public class GeometryUtil {

    /**
     * The SRID (Spatial Reference System Identifier) for WGS84.
     */
    private static final int SRID = 4326;

    /**
     * Creates a Point object from a PointDTO.
     *
     * @param pointDTO The PointDTO containing coordinate information
     * @return A Point object representing the geographic location
     * @throws IllegalArgumentException if pointDTO is null or has invalid coordinates
     */
    public static Point createPoint(PointDTO pointDTO) {
        if (pointDTO == null || pointDTO.getCoordinates() == null || pointDTO.getCoordinates().length < 2) {
            throw new IllegalArgumentException("Invalid PointDTO: null or insufficient coordinates");
        }

        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), SRID);
        Coordinate coordinate = new Coordinate(pointDTO.getCoordinates()[0], pointDTO.getCoordinates()[1]);
        return geometryFactory.createPoint(coordinate);
    }

    // Private constructor to prevent instantiation
    private GeometryUtil() {
        throw new AssertionError("Utility class should not be instantiated");
    }
}