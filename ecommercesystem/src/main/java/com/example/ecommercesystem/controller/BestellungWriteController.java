package com.example.ecommercesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommercesystem.dto.BestellMessage;
import com.example.ecommercesystem.dto.BestellungRequest;
import com.example.ecommercesystem.dto.BestellungResponse;
import com.example.ecommercesystem.entity.Bestellung;
import com.example.ecommercesystem.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/bestellungen")
public class BestellungWriteController {

    @Autowired
    private BestellungService service;
    private static final Logger logger = LoggerFactory.getLogger(BestellungService.class);

    /**
     * Bearbeitet eine Bestellungsanfrage und gibt die Bestellungsbestätigung
     * zurück.
     * 
     * @param request Die Bestellungsanfrage, die alle erforderlichen Informationen
     *                zur Bestellung enthält
     * @return Eine {@link BestellungResponse} mit den Details der aufgegebenen
     *         Bestellung
     */
    @PostMapping
    public ResponseEntity<BestellungResponse> bestellungAufgeben(@RequestBody BestellungRequest request) {
        logger.info("Bestellungsanfrage erhalten von Kunde mit ID: {}", request.getCustomerid());
        logger.debug("Bestellungsanfrage Details: {}", request);

        Bestellung bestellung = service.bestellungAufgeben(request);

        // Nachricht an das CRM-System senden sobald Bestellung (Kauf) eingeht
        BestellMessage message = new BestellMessage(
                bestellung.getOrderid(),
                bestellung.getOrderdate(),
                bestellung.getQuantity(),
                bestellung.getDeliverystatus());
        service.sendOrderToCrm(message);

        // Bestellung in Response umwandeln
        BestellungResponse response = new BestellungResponse(
                bestellung.getDeliverydate(),
                bestellung.getDeliverystatus());

        // Nur das Response-DTO zurückgeben
        return ResponseEntity.ok(response);
    }
}
