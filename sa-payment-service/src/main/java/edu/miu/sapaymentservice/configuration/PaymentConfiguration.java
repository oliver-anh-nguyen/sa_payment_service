package edu.miu.sapaymentservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class PaymentConfiguration {
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("ReservationOrderEvent")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicPayment() {
        return TopicBuilder.name("PaymentOrderEvent")
                .partitions(10)
                .replicas(1)
                .build();
    }
}
