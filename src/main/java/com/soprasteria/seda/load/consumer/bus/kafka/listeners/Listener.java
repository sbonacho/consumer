package com.soprasteria.seda.load.consumer.bus.kafka.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soprasteria.seda.load.consumer.measures.Measure;
import com.soprasteria.seda.load.model.ExecutionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Listener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

    @Value("${connector.topics.test}")
    private String topic;

    ObjectMapper mapper = new ObjectMapper();

    Measure measure;

    @KafkaListener(topics = "${connector.topics.test}")
    public void consumeTest(String event) {
        measure.add(event.length(), false);
        if (measure.isFinished()) {
            LOGGER.info("{}", measure);
        }
    }

    @KafkaListener(topics = "${connector.topics.control}")
    public void consumeControl(String json) {
        try {
            ExecutionConfig config = mapper.readValue(json, ExecutionConfig.class);
            if (config.getTopic().equals(topic)) {
                measure = new Measure(config.getTopic(), config.getMessages());
            } else {
                LOGGER.info("This consumer is not going to consume this test: Test topic is: \"{}\" configured is \"{}\"", config.getTopic(), topic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}