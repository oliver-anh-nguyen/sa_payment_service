package edu.miu.sapaymentservice.controller;

import edu.miu.sapaymentservice.entity.Order;
import edu.miu.sapaymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/order")
    public void send(@RequestBody Order order) {
        paymentService.publish("ReservationOrderEvent", order);
    }

    @PostMapping("/payment")
    public void sendInfoPayment(@RequestBody Order order) {
        paymentService.publish("PaymentOrderEvent", order);
    }
}
