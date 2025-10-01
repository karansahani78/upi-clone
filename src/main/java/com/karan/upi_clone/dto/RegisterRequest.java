package com.karan.upi_clone.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String mobile;
    private String password;
    private String requestedVpa;
    private double initialBalance;
}
