package com.example.web_demo.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig implements ApplicationListener<ContextRefreshedEvent> {
	//logger
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    
    @Autowired
    private DataSource dataSource;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try (Connection connection = dataSource.getConnection()) {
            logger.info("Connected to database: {}", connection.getMetaData().getURL());
//            System.out.println("Connected to database: " + connection.getMetaData().getURL());
        } catch (SQLException e) {
            logger.error("Failed to connect to database: {}", e.getMessage());
        }
    }
}
