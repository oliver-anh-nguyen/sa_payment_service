package edu.miu.creditservice.service;

import edu.miu.creditservice.entity.Credit;
import edu.miu.creditservice.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;

    private final KafkaTemplate<String, Credit> kafkaTemplate;

    @Override
    public void publish(String topic, Credit message) {
        kafkaTemplate.send(topic, message);
    }

    @Override
    @KafkaListener(id = "paymentId", topics = "${kafka.topicCredit}")
    public void listenPayment(Credit credit) {
        System.out.println("Received info from credit topic: " + credit);
        if (credit != null) {
            save(credit);
        }
    }
    @Transactional
    public void save(Credit credit) {
        credit.setId(UUID.randomUUID());
        creditRepository.save(credit);
    }

    public Credit getById(UUID id) {
        return creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find transaction credit with id: " + id));
    }
}
