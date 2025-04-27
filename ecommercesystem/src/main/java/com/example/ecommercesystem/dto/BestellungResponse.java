package com.example.ecommercesystem.dto;

import com.example.ecommercesystem.entity.Deliverystatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Antwortobjekt für Bestellungen.
 * 
 * Enthält Informationen über das geplante Lieferdatum und den aktuellen
 * Lieferstatus einer Bestellung.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BestellungResponse {
    private LocalDate deliverydate;
    private Deliverystatus deliverystatus;
}