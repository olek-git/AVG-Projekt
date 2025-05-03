package com.acme.crmsystem.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.acme.crmsystem.entity.BestellHistorie;
import com.acme.crmsystem.service.BestellHistorieService;

public class BestellHistorieListener {

    @Autowired
    private BestellHistorieService bestellHistorieService;

    @RabbitListener(queues = "orderQueue")
    public void receiveOrderMessage(BestellHistorie bestellHistorie) {

        bestellHistorieService.updateBestellHistorie(bestellHistorie);
    }
}
