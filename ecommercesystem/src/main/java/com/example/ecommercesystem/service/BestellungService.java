package com.example.ecommercesystem.service;

import com.example.ecommercesystem.dto.BestellMessage;
import com.example.ecommercesystem.dto.BestellungRequest;
import com.example.ecommercesystem.dto.BestellungResponse;
import com.example.ecommercesystem.entity.Bestellung;
import com.example.ecommercesystem.entity.Deliverystatus;
import com.example.ecommercesystem.rabbitmq.RabbitConfig;
import com.example.ecommercesystem.repository.BestellungRepository;

import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

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
        bestellung.setOrderid(UUID.randomUUID().toString());
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

        return repository.save(bestellung);
    }

    /**
     * Aktualisiert den Status einer bestehenden Bestellung.
     * 
     * Diese Methode sucht eine Bestellung anhand der Bestell-ID und aktualisiert
     * den Lieferstatus sowie das Lieferdatum der Bestellung.
     * 
     * @param bestellungId Die ID der Bestellung, deren Status aktualisiert werden
     *                     soll
     * @param neuerStatus  Der neue Lieferstatus der Bestellung
     * @param lieferdatum  Das neue Lieferdatum der Bestellung
     */
    public void updateBestellungStatus(String bestellungId, Deliverystatus neuerStatus, LocalDate lieferdatum) {
        Optional<Bestellung> optionalBestellung = repository.findById(bestellungId);
        if (optionalBestellung.isPresent()) {
            Bestellung bestellung = optionalBestellung.get();
            bestellung.setDeliverystatus(neuerStatus);
            bestellung.setDeliverydate(lieferdatum);
            repository.save(bestellung);

            logger.info("Bestellung mit ID {} erfolgreich aktualisiert: neuer Status = {}, neues Lieferdatum = {}",
                    bestellungId, neuerStatus, lieferdatum);
        } else {
            logger.warn("Keine Bestellung gefunden für ID: {}", bestellungId);
        }
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
                RabbitConfig.EXCHANGE_NAME,
                RabbitConfig.ROUTING_KEY,
                bestellMessage);
        logger.info("Nachricht an CRM-System gesendet: {}", bestellMessage.getOrderid());
    }
}