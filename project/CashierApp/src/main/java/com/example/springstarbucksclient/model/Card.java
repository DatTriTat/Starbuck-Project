package com.example.springstarbucksclient.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private String cardNumber;
    private String cardCode;
    private double balance;
    private boolean activated;
    private String status;
}
