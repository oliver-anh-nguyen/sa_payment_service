package edu.miu.paypalservice.service;

import edu.miu.paypalservice.entity.Order;

public interface PaypalService {
    void publish(String topic, Order message);

    void listenPayment(Order message);
}
