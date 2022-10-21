package edu.miu.creditservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class CreditConfiguration {
    @Bean
    public NewTopic topicPaypal() {
        return TopicBuilder.name("CreditOrderEvent")
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
