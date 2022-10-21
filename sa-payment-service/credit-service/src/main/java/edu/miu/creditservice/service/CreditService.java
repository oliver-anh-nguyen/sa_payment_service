package edu.miu.creditservice.service;

import edu.miu.creditservice.entity.Order;

public interface CreditService {
    void publish(String topic, Order message);

    void listenPayment(Order message);
}
