package edu.miu.sapaymentservice.service;

import edu.miu.sapaymentservice.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Override
    public void publish(String topic, Order message) {
        kafkaTemplate.send(topic, message);
    }

    @Override
    @KafkaListener(id = "reserveId", topics = "ReservationOrderEvent")
    public void listen(Order message) {
        System.out.println("Received info from Order: " + message);
    }
}
