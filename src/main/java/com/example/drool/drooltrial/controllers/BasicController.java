package com.example.drool.drooltrial.controllers;

import com.example.drool.drooltrial.dao.RulesRepository;
import com.example.drool.drooltrial.exceptions.KieContainerNotInitiatedException;
import com.example.drool.drooltrial.model.Decision;
import com.example.drool.drooltrial.model.Fact;
import com.example.drool.drooltrial.model.Fare;
import com.example.drool.drooltrial.model.TaxiRide;
import com.example.drool.drooltrial.services.DroolsFactory;
import com.example.drool.drooltrial.services.DroolsService;
import com.example.drool.drooltrial.services.TaxiFareService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BasicController {

    @Autowired
    private TaxiFareService taxiFareService;

    @Autowired
    private RulesRepository rulesRepository;

    @Autowired
    private DroolsService droolsService;

    @GetMapping("/calculateFare")
    public Fare calculateFareController(@RequestParam("night") int isNight, @RequestParam("distance") int distance) {
        TaxiRide taxiRide;
        if (isNight == 0)
            taxiRide = new TaxiRide(false, distance);
        else
            taxiRide = new TaxiRide(true, distance);
        return taxiFareService.calculateFare(taxiRide);
    }

    @GetMapping("/calculateFareV2")
    public Decision calculateFareControllerV2(@RequestParam("night") int isNight, @RequestParam("distance") int distance)
            throws UnsupportedEncodingException, KieContainerNotInitiatedException {
        TaxiRide taxiRide;
        if (isNight == 0)
            taxiRide = new TaxiRide(false, distance);
        else
            taxiRide = new TaxiRide(true, distance);
        return droolsService.executeAllRules(taxiRide);
    }

    @GetMapping("/database")
    public Object databaseInterface() {
        return rulesRepository.findAll();
    }

    @GetMapping("/updateRules")
    public void updateRules() throws UnsupportedEncodingException {
        DroolsFactory.getDroolsFactoryInstance().updateRules(rulesRepository.findAll());
    }

    @GetMapping("/getRules")
    public Object getRules() throws UnsupportedEncodingException, KieContainerNotInitiatedException {
        List<String> rules = new ArrayList<>();
        DroolsFactory droolsFactory = DroolsFactory.getDroolsFactoryInstance();
        if(!droolsFactory.isInstantiated()) {
            droolsFactory.updateRules(rulesRepository.findAll());
        }
        KieSession kSession = droolsFactory.getKieSession();
        kSession.getKieBase().getKiePackages()
                .stream()
                .forEach(p -> {
                    p.getRules().stream().forEach(r -> {
                        rules.add(r.getName());
                    });
                });
        return rules;
    }

    @PostMapping(value = "/evaluateFact")
    public Decision evaluateFact(@RequestBody Fact fact) throws UnsupportedEncodingException, KieContainerNotInitiatedException {
        return droolsService.executeAllRules(fact);
    }
}
