package edu.miu.paypalservice.service;

import edu.miu.paypalservice.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaypalServiceImpl implements PaypalService {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Override
    public void publish(String topic, Order message) {
        kafkaTemplate.send(topic, message);
    }

    @Override
    @KafkaListener(id = "paymentId", topics = "PaymentOrderEvent")
    public void listenPayment(Order message) {
        String type = message.getPaymentType();
        if (type.equals("PAYPAL")) {
            if (message.getPaymentMap() == null) {
                // get info payment from Account
            } else {
                System.out.println("PAYPAL INFO");
                System.out.println("PAYPAL number: " + message.getPaymentMap().get("paypalNumber"));
                System.out.println("PAYPAL token: " + message.getPaymentMap().get("paypalToken"));
            }
        }
    }
}
