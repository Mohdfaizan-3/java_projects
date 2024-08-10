package com.uber.uberApp.dto;

import com.uber.uberApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RiderDTO {

    private User user;
    private Double rating;

}
