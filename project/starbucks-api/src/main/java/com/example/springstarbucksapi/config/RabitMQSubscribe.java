package com.example.springstarbucksapi.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabitMQSubscribe {

    private final String QUEUE_NAME;
    private final String EXCHANGE;
    private final String ROUTING_KEY;

    public RabitMQSubscribe(@Value("${queue.rabbitmq.queue}") String queueName,
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


}