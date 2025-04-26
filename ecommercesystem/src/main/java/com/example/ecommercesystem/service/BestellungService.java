package com.example.ecommercesystem.service;

import com.example.ecommercesystem.controller.BestellungRequest;
import com.example.ecommercesystem.controller.BestellungResponse;
import com.example.ecommercesystem.entity.Bestellung;
import com.example.ecommercesystem.entity.Deliverystatus;
import com.example.ecommercesystem.repository.BestellungRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BestellungService {

    @Autowired
    private BestellungRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(BestellungService.class);

    public BestellungResponse bestellungAufgeben(BestellungRequest request) {
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

        repository.save(bestellung);
        logger.info("Bestellung erfolgreich erstellt: {}", bestellung.getOrderid());

        return new BestellungResponse(lieferdatum, Deliverystatus.PENDING);
    }
}