package com.uber.uberApp.configs;

import com.uber.uberApp.dto.PointDTO;
import com.uber.uberApp.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Configuration class for ModelMapper.
 * This class sets up custom type mappings for conversion between PointDTO and Point objects.
 */
@Component
public class ModelMapperConfig {

    /**
     * Creates and configures a ModelMapper bean.
     *
     * @return A configured ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configure PointDTO to Point conversion
        modelMapper.typeMap(PointDTO.class, Point.class).setConverter(context -> {
            PointDTO pointDTO = context.getSource();
            return GeometryUtil.createPoint(pointDTO);
        });

        // Configure Point to PointDTO conversion
        modelMapper.typeMap(Point.class, PointDTO.class).setConverter(context -> {
            Point point = context.getSource();
            double[] coordinates = {
                    point.getX(),
                    point.getY()
            };
            return new PointDTO(coordinates);
        });

        return modelMapper;
    }
}