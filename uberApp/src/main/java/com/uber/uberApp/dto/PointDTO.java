package com.uber.uberApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PointDTO {

    private double[] coordinates;

    public PointDTO(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
