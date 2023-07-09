package com.example.springstarbucksapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "STARBUCKS_DRINKS")
@Data
@RequiredArgsConstructor
public class StarbucksDrinks {

    private @Id @JsonIgnore /* https://www.baeldung.com/jackson-ignore-properties-on-serialization */
    Long id;
    @Column(nullable = false)
    private String drink;
    @Column(nullable = false)
    private String milk;
    @Column(nullable = false)
    private String size;
    private double total;
    private String status;
    private String register;
}
