package com.example.ecommercesystem.controller;

import com.example.ecommercesystem.entity.Deliverystatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BestellungResponse {
    private LocalDate deliverydate;
    private Deliverystatus deliverystatus;
}