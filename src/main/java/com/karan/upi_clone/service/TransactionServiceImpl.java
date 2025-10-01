package com.karan.upi_clone.service;

import com.karan.upi_clone.dto.PaymentRequest;
import com.karan.upi_clone.model.Account;
import com.karan.upi_clone.model.Transaction;
import com.karan.upi_clone.repository.AccountRepository;
import com.karan.upi_clone.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Transaction makePayment(PaymentRequest req) {
        Account payer =accountRepository.findByVpa(req.getFromVpa()).orElseThrow(()->new RuntimeException("Payer not found"));
        Account payee =accountRepository.findByVpa(req.getToVpa()).orElseThrow(()->new RuntimeException("Payee not found"));

        if (payer.getBalance().compareTo(BigDecimal.valueOf(req.getAmount())) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        payer.setBalance(payer.getBalance().subtract(BigDecimal.valueOf(req.getAmount())));
        payee.setBalance(payee.getBalance().add(BigDecimal.valueOf(req.getAmount())));
        accountRepository.save(payer);
        accountRepository.save(payee);

        Transaction txn = Transaction.builder()
                .fromVpa(req.getFromVpa())
                .toVpa(req.getToVpa())
                .amount(String.valueOf(BigDecimal.valueOf(req.getAmount())))
                .status("SUCCESS")
                .createdAt(LocalDateTime.now())
                .build();
        return transactionRepository.save(txn);
    }
}

