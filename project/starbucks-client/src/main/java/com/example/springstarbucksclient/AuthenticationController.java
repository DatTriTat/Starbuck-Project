package com.example.springstarbucksclient;

import com.example.springstarbucksclient.model.Card;
import com.example.springstarbucksclient.model.Order;
import com.example.springstarbucksclient.model.Ping;
import com.example.springstarbucksclient.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/*
    RestTemplate JavaDoc:
        * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html
        * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpEntity.html

    Tutorial Resources:
        * https://reflectoring.io/spring-resttemplate
        * https://www.baeldung.com/rest-template
        * https://springframework.guru/enable-pretty-print-of-json-with-jackson
        * https://attacomsian.com/blog/spring-boot-resttemplate-get-request-parameters-headers

 */
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/login")
public class AuthenticationController {

    @Value("${starbucks.client.apikey}")
    String API_KEY;
    @Value("${starbucks.client.apihost}")
    String API_HOST;
    @Value("${starbucks.client.register}")
    String REGISTER;

    @GetMapping
    public String getAction(@ModelAttribute("command") ConsoleCommand command,
            Model model) {
        return "login";
    }

    @PostMapping
    public String postAction(@ModelAttribute("command") ConsoleCommand command,
            @RequestParam(value = "action", required = true) String action, Errors errors, Model model,
            HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", API_KEY);
        String message = "";
        String resourceUrl = "";
        if (action.equals("SIGN UP")) {
            if (command.getPassword().equals(command.getRepassword())) {
                message = "SIGN UP";
                resourceUrl = "http://" + API_HOST + "/register";
                User userRequest = new User();
                userRequest.setUsername(command.getUsername());
                userRequest.setPassword(command.getPassword());
                HttpEntity<User> newUserRequest = new HttpEntity<User>(userRequest, headers);
                ResponseEntity<User> newUserResponse = restTemplate.postForEntity(resourceUrl, newUserRequest,
                        User.class);
            } else {
                log.info("Re-Password must be match with password");
                model.addAttribute("errorMessage", "Re-Password must match password");
            }
        } else if (action.equals("LOGIN")) {
            try {
                message = "LOGIN";
                resourceUrl = "http://" + API_HOST + "/login";
                User userRequest = new User();
                userRequest.setUsername(command.getUsername());
                userRequest.setPassword(command.getPassword());
                HttpEntity<User> newUserRequest = new HttpEntity<User>(userRequest, headers);
                ResponseEntity<User> newUserResponse = restTemplate.postForEntity(resourceUrl, newUserRequest,
                        User.class);
                User newUser = newUserResponse.getBody();
                return "redirect:/";
            } catch (Exception e) {
                log.info(e.getMessage());
                model.addAttribute("errorMessage", e.getMessage());
            }

        }
        return "login";
    }
}
