package com.springboot.csv;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.springboot.csv.*"})
public class SpringBatchBootToCsvApplication {
	 static Logger logger = Logger.getLogger(SpringBatchBootToCsvApplication.class.getName());
	    

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchBootToCsvApplication.class, args);
	}
	
	
	
	}

