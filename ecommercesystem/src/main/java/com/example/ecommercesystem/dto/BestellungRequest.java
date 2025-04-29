package com.example.ecommercesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

/**
 * Datenübertragungsobjekt für eingehende Bestellungsanfragen.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BestellungRequest {

    @NotBlank
    private String customerid;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    private String productid;

    @Min(1)
    private int quantity;

    @NotBlank
    private String paymentmethod;
}