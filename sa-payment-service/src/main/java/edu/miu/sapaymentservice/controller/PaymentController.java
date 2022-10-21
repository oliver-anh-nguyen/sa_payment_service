package edu.miu.sapaymentservice.controller;

import edu.miu.sapaymentservice.entity.Payment;
import edu.miu.sapaymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topic")
public class PaymentController {

    @Value("${kafka.topicPayment}")
    private String topicPayment;

    private final PaymentService paymentService;

    @PostMapping("/payment")
    public void send(@RequestBody Payment payment) {
        paymentService.publish(topicPayment, payment);
    }
}
