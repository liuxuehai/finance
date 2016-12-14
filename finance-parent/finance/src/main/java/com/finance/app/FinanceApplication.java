package com.finance.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.finance.component.job.PriceTask;

import org.springframework.boot.SpringApplication;

@SpringBootApplication
@ComponentScan(basePackages = "com.finance.component,com.finance.service")
public class FinanceApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(FinanceApplication.class, args);
		PriceTask task = context.getBean(PriceTask.class);
		Thread thread = new Thread(task);
		thread.start();
	}
}
