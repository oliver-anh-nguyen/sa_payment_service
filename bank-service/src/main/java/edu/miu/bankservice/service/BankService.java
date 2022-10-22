package edu.miu.bankservice.service;

import edu.miu.bankservice.entity.Bank;

public interface BankService {
    void publish(String topic, Bank message);

    void listenPayment(Bank message);
}
