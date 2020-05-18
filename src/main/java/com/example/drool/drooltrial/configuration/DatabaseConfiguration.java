package com.example.drool.drooltrial.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public SpringLiquibase getSpringLiquibaseBean() {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setChangeLog("classpath:db.changelog/changelog-master.yml");
        springLiquibase.setDataSource(getDataSource());
        return springLiquibase;
    }

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.type(org.h2.jdbcx.JdbcDataSource.class);
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:~/test");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

}
