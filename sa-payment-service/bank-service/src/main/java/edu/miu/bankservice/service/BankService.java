package edu.miu.bankservice.service;

import edu.miu.bankservice.entity.Order;

public interface BankService {
    void publish(String topic, Order message);

    void listenPayment(Order message);
}
