package com.example.springstarbucksclient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class SpringCashierController {

    @Value("${starbucks.client.apikey}")
    String API_KEY;
    @Value("${starbucks.client.apihost}")
    String API_HOST;
    @Value("${starbucks.client.register}")
    String REGISTER;

    @GetMapping
    public String getAction(@ModelAttribute("command") Command command,
            Model model, HttpSession session) {

        String message = "";
        String server_ip = "";
        String host_name = "";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            server_ip = ip.getHostAddress();
            host_name = ip.getHostName();
        } catch (Exception e) {
        }
        command.setRegister(command.getStores());
        model.addAttribute("message", message);
        model.addAttribute("server", host_name + "/" + server_ip);

        return "starbucks";

    }

    @PostMapping
    public String postAction(@ModelAttribute("command") Command command,
            @RequestParam(value = "action", required = true) String action,
            Errors errors, Model model, HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        command.setRegister(command.getStores());

        String resourceUrl = "";
        String message = "";
        String server_ip = "";
        String host_name = "";
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", API_KEY);
        HttpEntity<String> apikey = new HttpEntity<>(headers);
        Order newOrder = null;
        if (action.equals("Get Order")) {
            try {
                message = "";
                resourceUrl = "http://" + API_HOST + "/order/register/" + command.getStores();

                ResponseEntity<Order> newOrderResponse = restTemplate.exchange(resourceUrl, HttpMethod.GET, apikey,
                        Order.class);
                if (newOrderResponse.getStatusCodeValue() == 200) {
                    newOrder = newOrderResponse.getBody();
                    if (newOrder == null) {
                        message = "Starbucks Reserved Order" + "\n\n" +
                                "Register: " + command.getRegister() + "\n" +
                                "Status:   " + "Ready for New Order" + "\n";
                    } else {
                        message = "Starbucks Reserved Order" + "\n\n" +
                                "Drink: " + newOrder.getDrink() + "\n" +
                                "Milk:  " + newOrder.getMilk() + "\n" +
                                "Size:  " + newOrder.getSize() + "\n" +
                                "Total: " + newOrder.getTotal() + "\n" +
                                "\n" +
                                "Register: " + newOrder.getRegister() + "\n" +
                                "Status:   " + newOrder.getStatus() + "\n";
                    }
                } else {
                    message = "Starbucks Reserved Order" + "\n\n" +
                            "Register: " + command.getRegister() + "\n" +
                            "Status:   " + "Ready for New Order" + "\n";
                }
            } catch (Exception e) {
                log.info(e.getMessage());
                message = "Starbucks Reserved Order" + "\n\n" +
                        "Register: " + command.getRegister() + "\n" +
                        "Status:   " + "Ready for New Order" + "\n";
            }
        } else if (action.equals("Place Order")) {
            try {
                message = "";
                resourceUrl = "http://" + API_HOST + "/order/register/" + command.getStores();
                Order orderRequest = new Order();
                orderRequest.setDrink(command.getDrinkOption());
                orderRequest.setMilk(command.getMilkOption());
                orderRequest.setSize(command.getSizeOption());
                HttpEntity<Order> newOrderRequest = new HttpEntity<Order>(orderRequest, headers);
                ResponseEntity<Order> newOrderResponse = restTemplate.postForEntity(resourceUrl, newOrderRequest,
                        Order.class);
                newOrder = newOrderResponse.getBody();
                System.out.println(newOrder);
                message = "Starbucks Reserved Order" + "\n\n" +
                        "Drink: " + newOrder.getDrink() + "\n" +
                        "Milk:  " + newOrder.getMilk() + "\n" +
                        "Size:  " + newOrder.getSize() + "\n" +
                        "Total: " + newOrder.getTotal() + "\n" +
                        "\n" +
                        "Register: " + newOrder.getRegister() + "\n" +
                        "Status:   " + newOrder.getStatus() + "\n";
            } catch (Exception e) {
                log.info(e.getMessage());
                message = "Clearing an Active Order before creating a new one";
            }
        } else if (action.equals("Clear Order")) {
            try {
                message = "";
                resourceUrl = "http://" + API_HOST + "/order/register/" + command.getStores();
                ResponseEntity<Message> response = restTemplate.exchange(resourceUrl, HttpMethod.DELETE, apikey,
                        Message.class);
                Message Messageresponse = response.getBody();
                message = Messageresponse.getStatus();
                newOrder = null;
                System.out.println(message);
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
