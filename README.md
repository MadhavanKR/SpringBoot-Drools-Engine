## Drools with KIE

### Kie Services
* KieServices is the interface from where it possible to access all the Kie building and runtime facilities
<br/>
* For example, if we need a KieFileSystem object then we can get using kieServices.newKieFileSystem() <br\>
* if you want a kieBuilder, then kieService.newKieBuilder().. so on 

### Kie Modules
* Drools require an important configuration file called kmodule.xml where we can declaratively configure the KieBase(s) and KieSession(s) that can be created from a KIE project.
<br/>
* This xml file is expected to be available under resources/META-INF folder.
<br/>
* However, we can also create the module programmatically through kieBuilder like kieBuilder.getKieModule()

### KieBase and KieSession
* KieBase is a repository of all the application's knowledge definitions. It will contain rules, processes, functions, and type models.
<br/>
* The KieBase itself does not contain data; instead, sessions are created from the KieBase into which data can be inserted and from which process instances may be started.

* It is created from the KieBase or more easily can be created directly from the KieContainer if it has been defined in the kmodule.xml file

More detailed explanation can be found at: https://docs.jboss.org/drools/release/6.2.0.CR3/drools-docs/html/KIEChapter.html

 