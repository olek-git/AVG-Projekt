package com.acme.crmsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Repräsentiert einen Kunden im CRM-System.
 * 
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kunde {

    @Id
    private String customerid;

    private String name;

    private String email;

    private String phone;

    private String address;

    @Enumerated(EnumType.STRING)
    private PreferredContactMethod preferredcontactmethod;
}
