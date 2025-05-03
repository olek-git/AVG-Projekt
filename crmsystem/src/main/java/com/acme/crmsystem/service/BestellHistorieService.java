package com.acme.crmsystem.service;

import com.acme.crmsystem.entity.BestellHistorie;
import com.acme.crmsystem.repository.BestellHistorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BestellHistorieService {

    @Autowired
    private BestellHistorieRepository bestellHistorieRepository;

    public void updateBestellHistorie(BestellHistorie bestellHistorie) {
        // Überprüfe, ob eine Bestellung mit der gegebenen OrderID bereits existiert
        BestellHistorie existingOrder = bestellHistorieRepository.findByOrderId(bestellHistorie.getOrderid());

        if (existingOrder != null) {
            // Bestellung existiert bereits, also aktualisiere sie
            existingOrder.setOrderdate(bestellHistorie.getOrderdate());
            existingOrder.setTotalamount(bestellHistorie.getTotalamount());
            existingOrder.setStatus(bestellHistorie.getStatus());
            // Speichere die aktualisierte Bestellung
            bestellHistorieRepository.save(existingOrder);
        } else {
            // Bestellung existiert noch nicht, also speichere eine neue Bestellung
            bestellHistorieRepository.save(bestellHistorie);
        }
    }
}
