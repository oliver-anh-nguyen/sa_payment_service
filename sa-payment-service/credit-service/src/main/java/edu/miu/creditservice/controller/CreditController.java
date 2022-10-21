package edu.miu.creditservice.controller;

import edu.miu.creditservice.entity.Order;
import edu.miu.creditservice.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class CreditController {
    private final CreditService creditService;

    @PostMapping("/credit/pay")
    public void send(@RequestBody Order order) {
        creditService.publish("CreditOrderEvent", order);
    }
}
