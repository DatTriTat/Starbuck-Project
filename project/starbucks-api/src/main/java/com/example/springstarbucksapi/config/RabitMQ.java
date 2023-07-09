package com.example.springstarbucksapi.config;

import com.example.springstarbucksapi.model.RabbitMQMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabitMQ {

    private final String QUEUE_NAME;

    public RabitMQ(@Value("${queue.rabbitmq.queue}") String queueName) {
        QUEUE_NAME = queueName;
    }

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME);
	}

	public Object receive() {
		Object receivedMessage = rabbitTemplate.receiveAndConvert(QUEUE_NAME);
		return receivedMessage;
	}

	public void send(RabbitMQMessage data) throws JsonProcessingException {
		rabbitTemplate.convertAndSend(QUEUE_NAME, new ObjectMapper().writeValueAsString(data));
	}

}
