package edu.miu.creditservice.service;

import edu.miu.creditservice.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditServiceImpl implements CreditService {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Override
    public void publish(String topic, Order message) {
        kafkaTemplate.send(topic, message);
    }

    @Override
    @KafkaListener(id = "paymentId", topics = "PaymentOrderEvent")
    public void listenPayment(Order message) {
        String type = message.getPaymentType();
        if (type.equals("CREDIT")) {
            if (message.getPaymentMap() == null) {
                // get info payment from Account
            } else {
                System.out.println("CREDIT INFO");
                System.out.println("CARD number: " + message.getPaymentMap().get("cardNumber"));
                System.out.println("CARD expires: " + message.getPaymentMap().get("cardExpires"));
                System.out.println("CARD security code: " + message.getPaymentMap().get("cardSecurityCode"));
            }
        }
    }
}
