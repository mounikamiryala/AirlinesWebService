package com.practice.airlines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PracticeApplication {
    private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(PracticeApplication.class, args);
	}

}
