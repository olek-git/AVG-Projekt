package com.example.ecommercesystem.rabbitmq;

import com.example.ecommercesystem.service.BestellungService;
import com.example.ecommercesystem.dto.*;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Listener für Bestellstatus-Updates über RabbitMQ.
 * 
 * Diese Klasse empfängt Nachrichten über den Bestellstatus und aktualisiert
 * den Status der Bestellung im System.
 */
@Service
public class BestellungStatusListener {

    private final BestellungService bestellungService;
    private static final Logger logger = LoggerFactory.getLogger(BestellungStatusListener.class);

    /**
     * Konstruktor, der den BestellungService injiziert.
     * 
     * @param bestellungService Der Service, der für die Bearbeitung der
     *                          Bestellstatus-Updates verantwortlich ist
     */
    public BestellungStatusListener(BestellungService bestellungService) {
        this.bestellungService = bestellungService;
    }

    /**
     * Empfängt ein Status-Update über RabbitMQ und leitet es zur Aktualisierung an
     * den BestellungService weiter.
     * 
     * @param nachricht Die empfangene Nachricht mit dem Bestellstatus-Update
     */
    @RabbitListener(queues = RabbitConfig.BESTELLUNG_STATUS_QUEUE)
    public void empfangeStatusUpdate(BestellungStatusUpdateMessage nachricht) {
        logger.info("Empfange Status-Update für Produkt-ID: {}", nachricht.getProductid());
        logger.debug("Empfangene Nachricht: {}", nachricht);

        bestellungService.updateBestellungStatus(nachricht.getProductid(), nachricht.getDeliverystatus(),
                nachricht.getDeliverydate());
    }
}