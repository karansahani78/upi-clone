package com.karan.upi_clone.controller;

import com.karan.upi_clone.dto.PaymentRequest;
import com.karan.upi_clone.model.Transaction;
import com.karan.upi_clone.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService txnService; // depends on interface

    @PostMapping("/pay")
    public Transaction pay(@RequestBody PaymentRequest req) {
        return txnService.makePayment(req);
    }
}

