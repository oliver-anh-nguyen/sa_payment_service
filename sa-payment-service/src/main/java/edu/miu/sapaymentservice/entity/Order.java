package edu.miu.sapaymentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String paymentType;
    private Map<String, String> paymentMap;
    private String email;
    private double price;
    private String accountName;
    private String city;
}
