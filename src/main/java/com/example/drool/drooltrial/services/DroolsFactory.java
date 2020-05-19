package com.example.drool.drooltrial.services;

import com.example.drool.drooltrial.dao.Rule;
import com.example.drool.drooltrial.exceptions.KieContainerNotInitiatedException;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.UnsupportedEncodingException;

public class DroolsFactory {

    private static DroolsFactory droolsFactory = new DroolsFactory();

    private DroolsFactory() {

    }

    private KieContainer kieContainer;

    public static DroolsFactory getDroolsFactoryInstance() {
        return droolsFactory;
    }

    public KieSession getKieSession() throws KieContainerNotInitiatedException {
        if(kieContainer == null) {
            throw new KieContainerNotInitiatedException("kie container is not instantiated. use updateRules method");
        }
        return kieContainer.newKieSession();
    }

    public boolean isInstantiated() {
        return !(kieContainer == null);
    }

    public synchronized void updateRules(Iterable<Rule> rules) throws UnsupportedEncodingException {
        try {
            KieServices kieServices = KieServices.Factory.get();
            KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
            for (Rule rule : rules) {
                System.out.println("am i here?" + "src/main/resources/" + rule.getRuleName() + ".drl");
                kieFileSystem.write("src/main/resources/" + rule.getRuleName() + ".drl",
                        kieServices.getResources().newByteArrayResource(rule.getRuleString().getBytes("utf-8")));
            }
            KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
            KieModule kieModule = kieBuilder.getKieModule();
            KieContainer newkieContainer = kieServices.newKieContainer(kieModule.getReleaseId());

            if (kieContainer != null) {
                System.out.println("dispoing the kiecontainer");
                kieContainer.dispose();
            }

            kieContainer = newkieContainer;
        } catch (Exception e) {
            System.out.println("error while updating kiecontainer, retaining old container");
            e.printStackTrace();
            throw e;
        }
    }

}
