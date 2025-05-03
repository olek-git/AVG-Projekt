package com.example.ecommercesystem.config;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMQLogAppender extends AppenderBase<ILoggingEvent> {

    private RabbitTemplate rabbitTemplate;

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        if (rabbitTemplate != null) {
            String message = eventObject.getFormattedMessage();
            rabbitTemplate.convertAndSend("log.exchange", "log.routing.key", message);
        }
    }
}