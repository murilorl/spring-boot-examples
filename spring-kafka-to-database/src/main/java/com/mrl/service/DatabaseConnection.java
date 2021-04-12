package com.mrl.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DatabaseConnection {

    @Value("${database.url}")
    private String url;

    @Value("${database.user.name}")
    private String user;

    @Value("${database.user.password}")
    private String password;

    @Value("${database.name}")
    private String db;

    @Value("${database.schema}")
    private String schema;

    @Value("${snowflake.warehouse}")
    private String warehouse;

    @Bean
    public Connection connection() {
        Properties props = new Properties();
        props.put("user", user);
        props.put("password", password);
        props.put("db", db);
        props.put("schema", schema);
        props.put("warehouse", warehouse);
        props.put("role", "DEV_MODIFY");

        try {
            return DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;

    }

}
