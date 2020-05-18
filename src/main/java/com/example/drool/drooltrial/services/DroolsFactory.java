package com.example.drool.drooltrial.services;

import com.example.drool.drooltrial.dao.Rule;
import com.example.drool.drooltrial.dao.RulesRepository;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class DroolsFactory {

    private static KieContainer kieContainer;

    public static KieContainer getKieContainer(Iterable<Rule> rules) throws UnsupportedEncodingException {
        if(kieContainer == null) {
          updateKieContainer(rules);
        }
        return kieContainer;
    }

    public static void updateKieContainer(Iterable<Rule> rules) throws UnsupportedEncodingException {
        if(kieContainer != null) {
            System.out.println("dispoing the kiecontainer");
            kieContainer.dispose();
        }
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        for(Rule rule: rules) {
            System.out.println("am i here?" + "src/main/resources/"+rule.getRuleName()+".drl");
            kieFileSystem.write("src/main/resources/"+rule.getRuleName()+".drl",
                    kieServices.getResources().newByteArrayResource(rule.getRuleString().getBytes("utf-8")));
        }
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        KieModule kieModule = kieBuilder.getKieModule();
        kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
    }

}
