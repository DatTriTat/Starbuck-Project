package com.example.springlombok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringLombokApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLombokApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        ValAndVarUserDemo.print() ;
        FieldLevelGetterSetterDemo.print() ;
        GetterSetterUserDemo.print() ;
        ConstructorUserDemo.print() ;
        DataUserDemo.print() ;
        NonNullUserDemo.print() ;

        return String.format("Hello %s!", name);
    }

}