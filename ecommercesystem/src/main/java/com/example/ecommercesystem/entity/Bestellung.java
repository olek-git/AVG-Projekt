package com.example.ecommercesystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bestellung {
    @Id
    private String orderid;

    private String customerid;

    private String email;

    private String address;

    private String productid;

    private int quantity;

    private LocalDate orderdate;

    @Enumerated(EnumType.STRING)
    private Deliverystatus deliverystatus;

    private LocalDate deliverydate;

    private String paymentmethod;
}