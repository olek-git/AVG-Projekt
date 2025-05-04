package com.example.ecommercesystem.dto;

import java.time.LocalDate;

import com.example.ecommercesystem.entity.Deliverystatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Nachricht für die Aktualisierung des Lieferstatus einer Bestellung.
 * 
 * Wird über RabbitMQ vom ERP-System an das E-Commerce-System gesendet.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BestellungStatusUpdateMessage {
    private String orderid;
    private Deliverystatus deliverystatus;
    private LocalDate deliverydate;
}
