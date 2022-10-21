package edu.miu.sapaymentservice.service;

import edu.miu.sapaymentservice.entity.Payment;

public interface PaymentService {
    void publish(String topic, Payment message);

    void listen(Payment message);
}
