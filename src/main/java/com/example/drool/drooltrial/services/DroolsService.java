package com.example.drool.drooltrial.services;

import com.example.drool.drooltrial.dao.RulesRepository;
import com.example.drool.drooltrial.exceptions.KieContainerNotInitiatedException;
import com.example.drool.drooltrial.model.Decision;
import org.drools.core.base.RuleNameMatchesAgendaFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class DroolsService {

    @Autowired
    private RulesRepository rulesRepository;

    public Object executeRules(Object fact, String ruleName)
            throws UnsupportedEncodingException, KieContainerNotInitiatedException {
        DroolsFactory droolsFactory = DroolsFactory.getDroolsFactoryInstance();
        if(!droolsFactory.isInstantiated()) {
            droolsFactory.updateRules(rulesRepository.findAll());
        }
        KieSession kSession = droolsFactory.getKieSession();
        kSession.insert(fact);
        Decision decision = new Decision();
        kSession.setGlobal("decision", decision);
        kSession.fireAllRules(new RuleNameMatchesAgendaFilter(ruleName));
        kSession.dispose();
        return  decision;
    }

    public Decision executeAllRules(Object fact) throws UnsupportedEncodingException, KieContainerNotInitiatedException {
        DroolsFactory droolsFactory = DroolsFactory.getDroolsFactoryInstance();
        if(!droolsFactory.isInstantiated()) {
            droolsFactory.updateRules(rulesRepository.findAll());
        }
        KieSession kSession = droolsFactory.getKieSession();
        kSession.insert(fact);
        Decision decision = new Decision();
        kSession.setGlobal("decision", decision);
        kSession.fireAllRules();
        kSession.dispose();
        return  decision;
    }

}
