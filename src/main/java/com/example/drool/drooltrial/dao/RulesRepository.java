package com.example.drool.drooltrial.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RulesRepository extends CrudRepository<Rule, Integer> {

    public List<Rule> findByRuleName(String ruleName);

}
