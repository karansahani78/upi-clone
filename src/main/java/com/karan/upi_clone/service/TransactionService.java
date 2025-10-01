package com.karan.upi_clone.service;

import com.karan.upi_clone.dto.PaymentRequest;
import com.karan.upi_clone.model.Transaction;

public interface TransactionService {
    Transaction makePayment(PaymentRequest req);
}
