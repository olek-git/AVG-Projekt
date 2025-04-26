package com.example.ecommercesystem.controller;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
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