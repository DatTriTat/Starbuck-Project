package com.example.springstarbucksclient.config;

import com.example.springstarbucksclient.model.RabbitMQMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitPublisher {

	private final String QUEUE_NAME;
	private final String EXCHANGE;
	private final String ROUTING_KEY;

	public RabbitPublisher(@Value("${queue.rabbitmq.queue}") String queueName,
						   @Value("${queue.rabbitmq.exchange}") String exchange,
						   @Value("${queue.rabbitmq.routingkey}") String routingkey){
		QUEUE_NAME = queueName;
		EXCHANGE = exchange;
		ROUTING_KEY = routingkey;
	}

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
	}

	public void send(RabbitMQMessage data) throws JsonProcessingException {
		rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, new ObjectMapper().writeValueAsString(data));
	}

}