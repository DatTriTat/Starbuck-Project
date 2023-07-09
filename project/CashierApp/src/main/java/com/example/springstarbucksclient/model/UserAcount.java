package com.example.springstarbucksclient.model;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAcount {
    private String username;
    private String password;
    private String roles;

}
