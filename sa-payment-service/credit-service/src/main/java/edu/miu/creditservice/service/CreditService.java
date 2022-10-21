package edu.miu.creditservice.service;

import edu.miu.creditservice.entity.Credit;

public interface CreditService {
    void publish(String topic, Credit message);

    void listenPayment(Credit message);
}
