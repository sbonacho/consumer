package com.sbonacho.seda.load.consumer;

import com.sbonacho.seda.load.consumer.bus.kafka.listeners.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerBoot implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerBoot.class);

	@Autowired
	Listener listener;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ConsumerBoot.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Service Listening!");
	}
}
