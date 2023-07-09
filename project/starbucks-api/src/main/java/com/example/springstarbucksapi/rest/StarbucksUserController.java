package com.example.springstarbucksapi.rest;

import com.example.springstarbucksapi.model.StarbucksCard;
import com.example.springstarbucksapi.model.StarbucksOrder;
import com.example.springstarbucksapi.model.StarbucksUser;
import com.example.springstarbucksapi.service.StarbucksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/* 
    https://spring.io/guides/tutorials/rest/ 
    https://www.baeldung.com/building-a-restful-web-service-with-spring-and-java-based-configuration
*/

@RestController
public class StarbucksUserController {

    // REF:
    // https://www.moreofless.co.uk/spring-mvc-java-autowired-component-null-repository-service
    @Autowired
    private StarbucksService service;

    static class Message {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String msg) {
            status = msg;
        }
    }

    /* Get List of Starbucks User */
    @PostMapping("/login")
    StarbucksUser login(@RequestBody StarbucksUser user) {
        StarbucksUser newUser = service.login(user);
        if (newUser != null) {
            return newUser;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not Found!");
        }
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    Message register(@RequestBody StarbucksUser user) {
        Message msg = new Message();
        String str = service.register(user);
        msg.setStatus(str);
        return msg;
    }

}
