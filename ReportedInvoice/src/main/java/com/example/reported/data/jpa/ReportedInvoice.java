

package com.example.reported.data.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ReportedInvoice {

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(ReportedInvoice.class, args);
	}

}
