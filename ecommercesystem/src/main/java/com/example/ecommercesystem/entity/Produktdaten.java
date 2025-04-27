package com.example.ecommercesystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Repräsentiert ein Produkt im E-Commerce-System.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produktdaten {
    @Id
    private String productid;
    private String productname;
    private String category;
    private double price;
    private int stockquantity;
}