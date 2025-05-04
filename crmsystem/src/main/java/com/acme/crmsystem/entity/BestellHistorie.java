package com.acme.crmsystem.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Repräsentiert die Bestellhistorie im CRM-System
 * 
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestellHistorie {

    @Id
    private String orderid; // stammt vom E-Commerce-System

    private LocalDate orderdate; // Local Date weil besser als java.util.Date

    private double totalamount;

    private int status;
}
