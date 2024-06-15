package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Value("${coach.name}")
    private String coach;

    @Value("${team.name}")
    private String team;

    @GetMapping("/teaminfo")
    public String getTeamInfo () {
        return coach + " " + team;
    }
}
