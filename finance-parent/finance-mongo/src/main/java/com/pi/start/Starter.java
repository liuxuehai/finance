package com.pi.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pi.service.stock.StockDisruptor;

public class Starter {

	private static Logger logger = LoggerFactory.getLogger(Starter.class);
	static ApplicationContext context;
	static StockDisruptor stockInfoDisruptor;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");

	}

}
