package edu.miu.bankservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class BankConfiguration {
    @Bean
    public NewTopic topicPaypal() {
        return TopicBuilder.name("BankOrderEvent")
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
