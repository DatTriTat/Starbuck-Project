package com.example.springstarbucksclient;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
class ConsoleCommand {

    private String action ;
    private String username ;
    private String password ;
    private String repassword ;
    private String message ;

}

@Data
@RequiredArgsConstructor
class Command {

    private String action ;
    private String message ;
    private String stores ;
    private String register ; 
    private String drinkOption ;
    private String milkOption ;
    private String sizeOption ;
}
