package com.finance.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.boot.SpringApplication;

@SpringBootApplication
@ComponentScan(basePackages = "com.finance.component,com.finance.service,com.finance.controller")
public class FinanceApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(FinanceApplication.class, args);
	}
}
