package com.example.springstarbucksclient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.springstarbucksclient.model.Message;
import com.example.springstarbucksclient.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class SpringCashierController {

    @GetMapping
    public String getAction(@ModelAttribute("command") Command command,
            Model model, HttpSession session) {

        String message = "";

        String server_ip = "";
        String host_name = "";

        model.addAttribute("message", message);
        model.addAttribute("server", host_name + "/" + server_ip);

        return "starbucks";

    }

    @PostMapping
    public String postAction(@ModelAttribute("command") Command command,
            @RequestParam(value = "action", required = true) String action,
            Errors errors, Model model, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "";
        String message = "";
        String server_ip = "";
        String host_name = "";
        if (action.equals("Get Order")) {
            try {
                message = "";
                resourceUrl = "http://localhost:8080/order/register/" + command.getStores();
                ResponseEntity<Order> newOrderResponse = restTemplate.getForEntity(resourceUrl, Order.class);
                Order newOrder = newOrderResponse.getBody();
                System.out.println(newOrder);
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newOrder);
                    System.out.println(jsonString);
                    message = "\n" + jsonString;
                } catch (Exception e) {
                }
            } catch (Exception e) {
                log.info(e.getMessage());
                message = e.getMessage();
            }
        } else if (action.equals("Place Order")) {
            try {
                message = "";
                resourceUrl = "http://localhost:8080/order/register/" + command.getStores();
                Order orderRequest = new Order();
                orderRequest.setDrink(command.getDrinkOption());
                orderRequest.setMilk(command.getMilkOption());
                orderRequest.setSize(command.getSizeOption());
                HttpEntity<Order> newOrderRequest = new HttpEntity<Order>(orderRequest);
                ResponseEntity<Order> newOrderResponse = restTemplate.postForEntity(resourceUrl, newOrderRequest,
                        Order.class);
                Order newOrder = newOrderResponse.getBody();
                System.out.println(newOrder);
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newOrder);
                    System.out.println(jsonString);
                    message = "\n" + jsonString;
                } catch (Exception e) {
                }
            } catch (Exception e) {
                log.info(e.getMessage());
                message = e.getMessage();
            }
        } else if (action.equals("Clear Order")) {
            try {
                message = "";
                resourceUrl = "http://localhost:8080/order/register/" + command.getStores();
                ResponseEntity<Message> response = restTemplate.exchange(resourceUrl, HttpMethod.DELETE, null,
                        Message.class);
                message = response.getBody().getStatus();
            } catch (Exception e) {
                log.info(e.getMessage());
                message = e.getMessage();
            }
        }
        try {
            InetAddress ip = InetAddress.getLocalHost();
            server_ip = ip.getHostAddress();
            host_name = ip.getHostName();
        } catch (Exception e) {
        }

        model.addAttribute("message", message);
        model.addAttribute("server", host_name + "/" + server_ip);

        return "starbucks";

    }

}
