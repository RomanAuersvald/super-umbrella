package com.reported.sparest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SparestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SparestApplication.class, args);
	}

}
