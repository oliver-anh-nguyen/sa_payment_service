package edu.miu.sapaymentservice.service;

import edu.miu.sapaymentservice.entity.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final KafkaTemplate<String, Payment> kafkaTemplate;

    @Override
    public void publish(String topic, Payment message) {
        kafkaTemplate.send(topic, message);
    }

    @Override
    @KafkaListener(id = "reserveId", topics = "${kafka.topicPayment}")
    public void listen(Payment payment) {
        System.out.println("Received info from topicPayment: " + payment);
        publish(payment.getPaymentType().toUpperCase() + "_TOPIC", payment);
    }
}
