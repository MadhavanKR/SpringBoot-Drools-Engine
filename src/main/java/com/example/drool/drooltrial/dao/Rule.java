package com.example.drool.drooltrial.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rules_table")
public class Rule {

    @Id
    @Column(name = "rule_id")
    private Integer ruleId;

    @Column(name = "rule_name")
    private String ruleName;

    @Column(name = "rule_string")
    private String ruleString;

}
