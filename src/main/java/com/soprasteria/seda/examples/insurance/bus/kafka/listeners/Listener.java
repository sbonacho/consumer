package com.soprasteria.seda.examples.insurance.bus.kafka.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Listener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    private int cont = 0;

    @KafkaListener(topics = "${connector.topics.app}")
    public void consumeEventApp(String event) {
        cont++;
        LOGGER.info("Event Received -> {}, {}", event, cont);
    }
}
