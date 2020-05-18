package com.example.drool.drooltrial.configuration;

import org.drools.core.impl.InternalKnowledgeBase;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;

//@Configuration
public class DroolsConfiguration {

    private static final String drlFile = "taxiFare.drl";

    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile));
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        KieModule kieModule = kieBuilder.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    /*@Bean
    public void try() {
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        try {
            knowledgeBuilder.add(ResourceFactory.newByteArrayResource("rule".getBytes("utf-8")), ResourceType.DRL);
            InternalKnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }*/
}
