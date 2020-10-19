package com.WEBAPP.WEBAPP;

import com.WEBAPP.WEBAPP.web.RegistrationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import com.WEBAPP.WEBAPP.config.SecurityConfiguration;
import com.WEBAPP.WEBAPP.config.WebConfig;

@SpringBootApplication
public class WebappApplication extends SpringBootServletInitializer {



	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

}
