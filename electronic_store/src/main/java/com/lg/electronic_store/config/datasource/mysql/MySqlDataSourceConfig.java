package com.lg.electronic_store.config.datasource.mysql;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MySqlDataSourceConfig {

    @ConfigurationProperties("spring.datasource.mysql")
    @Bean
    public DataSourceProperties mySqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource mySqlDataSource() {
        return mySqlDataSourceProperties().initializeDataSourceBuilder().build();
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUsername(mySqlDataSourceProperties().getUsername());
//        dataSource.setPassword(mySqlDataSourceProperties().getPassword());
//        dataSource.setUrl(mySqlDataSourceProperties().getUrl());
//        dataSource.setDriverClassName(mySqlDataSourceProperties().getDriverClassName());
//        return dataSource;
    }
}
