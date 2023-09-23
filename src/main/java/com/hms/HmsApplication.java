package com.hms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@ComponentScan({"com.hms.security","com.hms.service","com.hms.service.implementation","com.hms.repositories"})
public class HmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmsApplication.class, args);
	}
}
