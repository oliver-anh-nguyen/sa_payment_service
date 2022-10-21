package edu.miu.bankservice.controller;

import edu.miu.bankservice.entity.Order;
import edu.miu.bankservice.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class BankController {
    private final BankService bankService;

    @PostMapping("/bank/pay")
    public void send(@RequestBody Order order) {
        bankService.publish("BankOrderEvent", order);
    }
}
