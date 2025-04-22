package com.example.ecommerce.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.listener.MessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Configuration
@EnableRabbit // Aktiviert die RabbitMQ-Listener
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "orderQueue";
    public static final String EXCHANGE_NAME = "orderExchange";
    public static final String ROUTING_KEY = "orderRoutingKey";

    // Queue erstellen
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);  // 'true' für persistente Nachrichten
    }

    // DirectExchange erstellen
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // Binding für Exchange und Queue erstellen
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
    }

    // Listener Container einrichten
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory, MessageListener messageListener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(messageListener);
        return container;
    }
}