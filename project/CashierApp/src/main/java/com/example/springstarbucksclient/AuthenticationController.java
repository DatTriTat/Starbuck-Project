package com.example.springstarbucksclient;

import com.example.springstarbucksclient.model.Message;
import com.example.springstarbucksclient.model.UserAcount;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

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

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping
public class AuthenticationController {

    @Value("${starbucks.client.apikey}")
    String API_KEY;
    @Value("${starbucks.client.apihost}")
    String API_HOST;
    @Value("${starbucks.client.register}")
    String REGISTER;

    @GetMapping(path = {"/signin", "/login"})
    public String getLoginAction(@ModelAttribute("command") ConsoleCommand command,
                                 Model model) {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/signin";
    }

    @PostMapping("/signin")
    public String postLoginAction(@ModelAttribute("command") ConsoleCommand command,
                                  @RequestParam(value = "action", required = true) String action, Errors errors, Model model,
                                  HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", API_KEY);
        String message = "";
        String resourceUrl = "";
        if (action.equals("LOGIN")) {
            try {
                message = "LOGIN";
                resourceUrl = "http://" + API_HOST + "/login";
                UserAcount userRequest = new UserAcount();
                userRequest.setUsername(command.getUsername());
                userRequest.setPassword(command.getPassword());
                System.out.println(userRequest);
                HttpEntity<UserAcount> newUserRequest = new HttpEntity<UserAcount>(userRequest, headers);
                ResponseEntity<UserAcount> newUserResponse = restTemplate.postForEntity(resourceUrl, newUserRequest,
                        UserAcount.class);
                if (newUserResponse.getStatusCodeValue() == 200) {
                    UserAcount newUser = newUserResponse.getBody();
                    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                    GrantedAuthority grantedAuthority = new GrantedAuthority() {
                        //anonymous inner type
                        public String getAuthority() {
                            return newUser.getRoles();
                        }
                    };
                    grantedAuthorities.add(grantedAuthority);
                    Authentication authentication =
                            new UsernamePasswordAuthenticationToken(newUser.getUsername(), null, grantedAuthorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    return "redirect:/";
                }
            } catch (Exception e) {
                log.info(e.getMessage(), e);
                model.addAttribute("errorMessage", "User Not Found!");
            }
        }
        return "login";
    }

    @GetMapping("/register")
    public String getSignUpAction(@ModelAttribute("command") ConsoleCommand command,
                                  Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String postSignUpAction(@ModelAttribute("command") ConsoleCommand command,
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
                UserAcount userRequest = new UserAcount();
                userRequest.setUsername(command.getUsername());
                userRequest.setPassword(command.getPassword());
                HttpEntity<UserAcount> newUserRequest = new HttpEntity<UserAcount>(userRequest, headers);
                ResponseEntity<Message> newUserResponse = restTemplate.postForEntity(resourceUrl, newUserRequest,
                        Message.class);
                message = newUserResponse.getBody().getStatus();
                System.out.println(message);
                if (message.equals("Created")) {
                    return "redirect:/login";
                } else {
                    model.addAttribute("errorMessage", message);

                }
            } else {
                log.info("Re-Password must be match with password");
                model.addAttribute("errorMessage", "Re-Password must match password");
            }
        }
        return "register";
    }


}
