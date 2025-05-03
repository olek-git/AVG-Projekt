package com.example.ecommercesystem.dto;

import java.time.LocalDate;
import com.example.ecommercesystem.entity.Deliverystatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestellMessage {
    private String orderid;
    private LocalDate orderdate;
    private int quantity;
    private Deliverystatus deliverstatus;

}
