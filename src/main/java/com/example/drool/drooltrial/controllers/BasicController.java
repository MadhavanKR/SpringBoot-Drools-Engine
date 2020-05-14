package com.example.drool.drooltrial.controllers;

import com.example.drool.drooltrial.model.Fare;
import com.example.drool.drooltrial.model.TaxiRide;
import com.example.drool.drooltrial.services.TaxiFareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @Autowired
    private TaxiFareService taxiFareService;

    @GetMapping("/calculateFare")
    public Fare calculateFareController(@RequestParam("night") int isNight, @RequestParam("distance") int distance) {
        TaxiRide taxiRide;
        if (isNight == 0)
            taxiRide = new TaxiRide(false, distance);
        else
            taxiRide = new TaxiRide(true, distance);
        return taxiFareService.calculateFare(taxiRide);
    }

}
