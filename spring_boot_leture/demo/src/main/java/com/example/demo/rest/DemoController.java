package com.example.demo.rest;

import com.example.demo.DemoApplication;
import com.example.demo.common.BaseBallCoach;
import com.example.demo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;

    @Autowired
    public DemoController(Coach myCoach) {
        this.myCoach = myCoach;
    }
//    @Autowired
//    public DemoController(@Qualifier("baseBallCoach") Coach coach) {
//        this.myCoach = coach;
//    }

//    @Autowired
//    public void setMyCoach(Coach myCoach) {
//        this.myCoach = myCoach;
//    }

    //    @Autowired
//    public DemoController(Coach myCoach) {
//        this.myCoach = myCoach;
//    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkOut();
    }
}


