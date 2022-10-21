package edu.miu.paypalservice.service;

import edu.miu.paypalservice.entity.Paypal;

public interface PaypalService {
    void publish(String topic, Paypal message);

    void listenPayment(Paypal message);
}
