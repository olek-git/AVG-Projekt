package com.example.ecommercesystem.dto;

import java.io.Serializable;
import java.time.LocalDate;
import com.example.ecommercesystem.entity.Deliverystatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestellMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderid;
    private LocalDate orderdate;
    private int quantity;
    private Deliverystatus deliverstatus;

}
