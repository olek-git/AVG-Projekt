package com.example.ecommercesystem.service;

import com.example.ecommercesystem.dto.BestellMessage;
import com.example.ecommercesystem.dto.BestellungRequest;
import com.example.ecommercesystem.dto.BestellungResponse;
import com.example.ecommercesystem.entity.Bestellung;
import com.example.ecommercesystem.entity.Deliverystatus;
import com.example.ecommercesystem.grpc.ErpClient;
import com.example.ecommercesystem.repository.BestellungRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.UUID;

import org.example.grpc.OrderAck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service für die Verwaltung von Bestellungen.
 * 
 * Diese Klasse enthält die Geschäftslogik für das Erstellen und Aktualisieren
 * von Bestellungen
 * im E-Commerce-System.
 */
@Service
public class BestellungService {

    @Autowired
    private BestellungRepository repository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private static final Logger logger = LoggerFactory.getLogger(BestellungService.class);

    @Autowired
    private ErpClient erpClient;

    /**
     * Startet den Bestellprozess für einen Kunden und erstellt eine neue
     * Bestellung.
     * 
     * @param request Die Anfrage des Kunden, die alle Informationen zur Bestellung
     *                enthält
     * @return Eine {@link BestellungResponse} mit Lieferdatum und Status der
     *         Bestellung
     */
    public Bestellung bestellungAufgeben(BestellungRequest request) {
        logger.info("Starte Bestellungsprozess für Kunde mit ID: {}", request.getCustomerid());

        Bestellung bestellung = new Bestellung();
        String orderId = UUID.randomUUID().toString();
        
        //Speichern der Bestellung
        bestellung.setOrderid(orderId);
        bestellung.setCustomerid(request.getCustomerid());
        bestellung.setEmail(request.getEmail());
        bestellung.setAddress(request.getAddress());
        bestellung.setProductid(request.getProductid());
        bestellung.setQuantity(request.getQuantity());
        bestellung.setPaymentmethod(request.getPaymentmethod());

        bestellung.setOrderdate(LocalDate.now());
        bestellung.setDeliverystatus(Deliverystatus.PENDING);
        LocalDate lieferdatum = LocalDate.now().plusDays(3);
        bestellung.setDeliverydate(lieferdatum);

        logger.info("Bestellung erfolgreich erstellt: {}", bestellung.getOrderid());

        try {
        OrderAck ack = erpClient.sendOrder(orderId, request.getProductid(), request.getQuantity());

        bestellung.setDeliverystatus(Deliverystatus.valueOf(ack.getDeliveryStatus().toUpperCase()));
        bestellung.setDeliverydate(LocalDate.parse(ack.getDeliveryDate()));

        logger.info("Bestelluebersicht: Lieferdatum = {}, Status = {} ", ack.getDeliveryDate(), ack.getDeliveryStatus());

        } catch (Exception e) {
            logger.error("Fehler beim gRPC-Aufruf an ERP: {}", e.getMessage());

            // Fallback-Werte
            bestellung.setDeliverystatus(Deliverystatus.PENDING);
            bestellung.setDeliverydate(LocalDate.now().plusDays(3));
        }

        return repository.save(bestellung);
    }

    /**
     * Aktualisiert den Lieferstatus einer Bestellung.
     * 
     * @param orderID        Die ID der Bestellung, deren Status aktualisiert werden
     *                       soll
     * @param deliveryStatus Der neue Lieferstatus der Bestellung
     */
    public void updateBestellungStatus(String orderID, String deliveryStatus) {
        //TODO: Bestellung aktualisieren und in der Datenbank speichern
        logger.info("Empfangene Statusänderung:\n- Bestell-ID: {}\n- Neuer Lieferstatus: {}", orderID, deliveryStatus);
    }

    /**
     * Schickt bei Aktualisierung einer Bestellung oder neuanlegen eine Nachricht
     * über RabbitMQ
     * an das CRM-System
     * 
     * @param bestellMessage
     */
    public void sendOrderToCrm(BestellMessage bestellMessage) {
        rabbitTemplate.convertAndSend(
                "",
                "order.updates",
                bestellMessage);
        logger.info("Nachricht an CRM-System gesendet: {}", bestellMessage.getOrderid());
    }
}