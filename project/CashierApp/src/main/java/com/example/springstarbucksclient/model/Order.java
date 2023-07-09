package com.example.springstarbucksclient.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String drink;
    private String milk;
    private String size;
    private double total;
    private String status;
    private String register;

    
}
