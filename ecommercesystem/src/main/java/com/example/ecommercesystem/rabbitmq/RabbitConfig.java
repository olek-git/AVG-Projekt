package com.example.ecommercesystem.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Konfiguration für RabbitMQ.
 * 
 * Diese Klasse konfiguriert die benötigten RabbitMQ-Komponenten,
 * einschließlich der Queue, des DirectExchange und der Binding-Definition,
 * die für den Bestellstatus-Kommunikationskanal erforderlich sind.
 */
@Configuration
public class RabbitConfig {

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
}
