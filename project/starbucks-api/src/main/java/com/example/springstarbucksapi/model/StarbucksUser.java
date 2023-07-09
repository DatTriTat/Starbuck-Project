package com.example.springstarbucksapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "STARBUCKS_USER")
@Data
@RequiredArgsConstructor
public class StarbucksUser {

    private @Id
    @GeneratedValue
    @JsonIgnore  /* https://www.baeldung.com/jackson-ignore-properties-on-serialization */
    Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role = "USER";

}