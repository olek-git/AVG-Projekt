package com.acme.crmsystem.repository;

import com.acme.crmsystem.entity.BestellHistorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BestellHistorieRepository extends JpaRepository<BestellHistorie, String> {

    // Methode, um eine Bestellung anhand der OrderID zu finden
    BestellHistorie findByOrderid(String orderid);
}