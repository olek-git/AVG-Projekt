package com.example.ecommercesystem.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Konfiguration für RabbitMQ.
 * 
 * Diese Klasse konfiguriert die benötigten RabbitMQ-Komponenten,
 * einschließlich der Queue, des DirectExchange und der Binding-Definition,
 * die für den Bestellstatus-Kommunikationskanal erforderlich sind.
 */
@EnableRabbit
@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_NAME = "order.exchange";
    public static final String ROUTING_KEY = "order.created";
    public static final String QUEUE_NAME = "order.queue";
    public static final String BESTELLUNG_STATUS_QUEUE = "bestellung.status.queue";
    public static final String BESTELLUNG_STATUS_EXCHANGE = "bestellung.status.exchange";
    public static final String BESTELLUNG_STATUS_ROUTING_KEY = "bestellung.status.routingkey";

    /**
     * Definiert die Queue für Bestellstatus-Updates.
     * 
     * @return Eine neue Queue für den Bestellstatus
     */
    @Bean
    public Queue bestellungStatusQueue() {
        return new Queue(BESTELLUNG_STATUS_QUEUE, true);
    }

    /**
     * Definiert den DirectExchange für Bestellstatus-Updates.
     * 
     * @return Ein neuer DirectExchange für den Bestellstatus
     */
    @Bean
    public DirectExchange bestellungStatusExchange() {
        return new DirectExchange(BESTELLUNG_STATUS_EXCHANGE, true, false);
    }

    /**
     * Bindet die Queue an den Exchange mit dem Routing Key.
     * 
     * @param bestellungStatusQueue    Die zu bindende Queue
     * @param bestellungStatusExchange Der zu verwendende DirectExchange
     * @return Die erstellte Binding-Definition
     */
    @Bean
    public Binding bestellungStatusBinding(Queue bestellungStatusQueue, DirectExchange bestellungStatusExchange) {
        return BindingBuilder.bind(bestellungStatusQueue).to(bestellungStatusExchange)
                .with(BESTELLUNG_STATUS_ROUTING_KEY);
    }

    /**
     * Definiert den DirectExchange für das Logging.
     * 
     * @return Ein neuer DirectExchange für das Logging
     */
    @Bean
    public TopicExchange logExchange() {
        return new TopicExchange("log.exchange");
    }

    /**
     * Definiert die Queue für das Logging
     * 
     * @return Eine neue Queue für das Logging
     */
    @Bean
    public Queue logQueue() {
        return new Queue("log.queue");
    }

    /**
     * Bindet die Queue an den Exchange mit dem Routing Key.
     * 
     * @param logQueue    Die zu bindende Queue
     * @param logExchange Der zu verwendende DirectExchange
     * @return Die erstellte Binding-Definition
     */
    @Bean
    public Binding logBinding(Queue logQueue, TopicExchange logExchange) {
        return BindingBuilder.bind(logQueue).to(logExchange).with("log.routing.key");
    }

    /**
     * Teil um Nachricht an CRM System senden zu können
     */

    @Bean
    public Queue orderQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, DirectExchange orderExchange) {
        // Binding der Queue an den Exchange mit dem RoutingKey
        return BindingBuilder.bind(orderQueue)
                .to(orderExchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate template = new RabbitTemplate();
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
