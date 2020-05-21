# Comparision of different rules engine available in Java

Following are the options available for our consideration when we want to use a rule engine:
* Drools
* OpenL Tablets
* Easy Rules
* RuleBook

## Drools

* Drools is a Business Rules Management System (BRMS) solution.
* The rules can be externally stored either as an artifact in maven repository, as drl files in the resources folder or the rules can be stored in an external storage like database.
* This makes drools really powerful as we can change the rules without code change.
* Rules follow a drools syntax as specified here: https://www.tutorialspoint.com/drools/drools_rule_syntax.htm
* The similarity between drools rule syntax with native java code makes it easier to understand and write rules.

## OpenL Tablets

* OpenL Tablets is a business rules management system and a business rules engine based on Excel decision tables.
* The rules are stored in excel sheets.
* We've to create interfaces with appropiate methods to represent the rules.

## Easy Rules

* Easy Rules is a simple Java rules engine providing a lightweight and POJO based framework to define business. It can create complex rules from primitive ones by using the composite pattern.
* This framework, in contrast to the most traditional rules engines, doesn’t make use of XML files or any Domain Specific Language files to segregate rules from the application. It uses annotation-based classes and methods for injecting business logic into the application.
* The tight coupling to java code makes it less easy to update the rules dynamically and diverges from our need to maintain rules independent of code.

## RuleBook

* RuleBook is a Java framework that leverages Java 8 lambdas and the Chain of Responsibility Pattern to define rules using simple BDD approach
* It makes use of the concept of “Facts”, which is data supplied to rules. RuleBook allows rules to modify the state of facts, which then can be read and modified by rules further down the chain. For those rules that read in data (Facts) of one type and output a result of a different type, RuleBook has Decisions.
* RuleBook can be integrated with Spring using Java DSL.
* RuleBook also has similar drawbacks as Easy Rules with respect to independent maintainence of rules and dynamic updation of them.