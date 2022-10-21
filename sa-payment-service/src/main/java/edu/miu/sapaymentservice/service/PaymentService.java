package edu.miu.sapaymentservice.service;

import edu.miu.sapaymentservice.entity.Order;

public interface PaymentService {
    void publish(String topic, Order message);

    void listen(Order message);
}
