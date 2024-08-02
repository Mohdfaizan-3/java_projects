package com.uber.uberApp.dto;

import com.uber.uberApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RiderDTO {

    Long id;
    private User user;
    private Double rating;

}
