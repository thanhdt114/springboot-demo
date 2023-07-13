package com.example.web_demo.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
	private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);
	
	@Value("${server.port}")
	private String portServer;
	
	@Value("${travelover.openapi.prod-url}")
	private String prodUrl;
	
	@Bean
	public OpenAPI myOpenAPI() {
		String devUrl = "http://localhost:" + portServer;
		
	    Server devServer = new Server();
	    devServer.setUrl(devUrl);
	    devServer.setDescription("Server URL in Development environment");
	
		Server prodServer = new Server();
		prodServer.setUrl(prodUrl);
		prodServer.setDescription("Server URL in Production environment");
		
		Contact contact = new Contact();
		contact.setEmail("travelover@gmail.com");
		contact.setName("Travelover");
		contact.setUrl("https://www.travelover.com");
		
		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
		
		Info info = new Info()
		    .title("Travelover Management API")
			.version("1.0")
			.contact(contact)
			.description("This API exposes endpoints to manage tutorials.").termsOfService("https://www.travelover.com/terms")
			.license(mitLicense);
		
		return new OpenAPI()
				.info(info)
				.servers(List.of(devServer, prodServer));
	}
}

