package com.example.web_demo.component;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartedListener.class);
	
	@Autowired
    private DataSource dataSource;
	
	@Value("${server.port}")
	private String portServer;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
    	try (Connection connection = dataSource.getConnection()) {
            logger.info("Connected to database: {}", connection.getMetaData().getURL());
        } catch (SQLException e) {
            logger.error("Failed to connect to database: {}", e.getMessage());
        }
    	
    	String linkServer = "http://localhost:" + portServer + "/swagger-ui/index.html";
    	
        logger.info("Server is running on: " + linkServer);
    } 
}
