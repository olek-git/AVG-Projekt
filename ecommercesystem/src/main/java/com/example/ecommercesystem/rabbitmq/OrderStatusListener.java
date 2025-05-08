package com.example.ecommercesystem.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ecommercesystem.dto.StatusUpdateMessage;
import com.example.ecommercesystem.service.BestellungService;

@Component
public class OrderStatusListener {
    @Autowired
    private BestellungService bestellungService;  // Service zur Bearbeitung der Bestellung

    private static final Logger logger = LoggerFactory.getLogger(OrderStatusListener.class);
    
    @RabbitListener(queues = "order_updates")  // Der Name der Queue aus deinem RabbitPublisher
    public void receiveStatusUpdate(StatusUpdateMessage message) {
        // Nachricht empfangen und Lieferstatus aktualisieren
        bestellungService.updateBestellungStatus(
            message.getOrderID(),  // Bestellung ID
            message.getDeliveryStatus() // Neuer Status
        );
        logger.info("Nachricht empfangen");
    }
}
