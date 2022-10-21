package edu.miu.bankservice.service;

import edu.miu.bankservice.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankServiceImpl implements BankService {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Override
    public void publish(String topic, Order message) {
        kafkaTemplate.send(topic, message);
    }

    @Override
    @KafkaListener(id = "paymentId", topics = "PaymentOrderEvent")
    public void listenPayment(Order message) {
        String type = message.getPaymentType();
        if (type.equals("BANK")) {
            if (message.getPaymentMap() == null) {
                // get info payment from Account
            } else {
                System.out.println("BANK INFO");
                System.out.println("BANK account: " + message.getPaymentMap().get("bankAccount"));
                System.out.println("BANK routing: " + message.getPaymentMap().get("bankRouting"));
                System.out.println("BANK name: " + message.getPaymentMap().get("bankName"));
            }
        }
    }
}
