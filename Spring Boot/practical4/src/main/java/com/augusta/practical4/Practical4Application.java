package com.augusta.practical4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Practical4Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Practical4Application.class, args);

		Dev obj = context.getBean(Dev.class);
		obj.build2();
	}
}
