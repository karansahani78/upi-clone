package com.karan.upi_clone.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String fromVpa;
    private String toVpa;
    private double amount;
    private String note;
}
