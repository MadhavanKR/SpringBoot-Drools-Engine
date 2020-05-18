package com.example.drool.drooltrial.services;

import com.example.drool.drooltrial.model.Fare;
import com.example.drool.drooltrial.model.TaxiRide;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaxiFareService {

    //@Autowired
    private KieContainer kieContainer;

    public Fare calculateFare(TaxiRide taxiRide) {
        //create a new kieSession
        KieSession kieSession = kieContainer.newKieSession();
        Fare rideFare = new Fare();
        kieSession.setGlobal("rideFare", rideFare);
        kieSession.insert(taxiRide);
        kieSession.fireAllRules();
        kieSession.dispose();
        return rideFare;
    }
}
