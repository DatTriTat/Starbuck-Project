package com.example.springstarbucksapi.repository;

import com.example.springstarbucksapi.model.StarbucksUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarbucksUserRepository extends JpaRepository<StarbucksUser, Long> {
    StarbucksUser findByUsernameAndPassword(String username, String password);

    StarbucksUser findByUsername(String username);
}