package edu.miu.bankservice.service;

import edu.miu.bankservice.entity.Bank;
import edu.miu.bankservice.repository.BankRepository;
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
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    private final KafkaTemplate<String, Bank> kafkaTemplate;

    @Override
    public void publish(String topic, Bank message) {
        kafkaTemplate.send(topic, message);
    }

    @Override
    @KafkaListener(id = "paymentId", topics = "${kafka.topicBank}")
    public void listenPayment(Bank bank) {
        System.out.println("Received info from bank topic: " + bank);
        if (bank != null) {
            save(bank);
        }
    }
    @Transactional
    public void save(Bank bank) {
        bank.setId(UUID.randomUUID());
        bankRepository.save(bank);
    }

    public Bank getById(UUID id) {
        return bankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find transaction bank with id: " + id));
    }
}

