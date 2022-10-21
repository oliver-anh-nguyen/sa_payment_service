package edu.miu.paypalservice.controller;

import edu.miu.paypalservice.entity.Order;
import edu.miu.paypalservice.service.PaypalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class PaypalController {
    private final PaypalService paypalService;

    @PostMapping("/paypal/pay")
    public void send(@RequestBody Order order) {
        paypalService.publish("PaypalOrderEvent", order);
    }
}
