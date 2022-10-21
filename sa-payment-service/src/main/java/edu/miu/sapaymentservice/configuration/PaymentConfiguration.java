package edu.miu.sapaymentservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class PaymentConfiguration {

    @Value("${kafka.topicPayment}")
    private String topicPayment;

    @Bean
    public NewTopic topicPayment() {
        return TopicBuilder.name(topicPayment)
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicPaypal() {
        return TopicBuilder.name("PAYPAL_TOPIC")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicCredit() {
        return TopicBuilder.name("CREDIT_TOPIC")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicBank() {
        return TopicBuilder.name("BANK_TOPIC")
                .partitions(10)
                .replicas(1)
                .build();
    }
}
