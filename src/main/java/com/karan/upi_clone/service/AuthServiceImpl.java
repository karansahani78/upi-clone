package com.karan.upi_clone.service;

import com.karan.upi_clone.dto.RegisterRequest;
import com.karan.upi_clone.model.Account;
import com.karan.upi_clone.model.AppUser;
import com.karan.upi_clone.repository.AccountRepository;
import com.karan.upi_clone.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AppUserRepository appUserRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser registerUser(RegisterRequest registerRequest) {
        if (appUserRepository.findByMobile(registerRequest.getMobile()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        AppUser user = AppUser.builder()
                .name(registerRequest.getName())
                .mobile(registerRequest.getMobile())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        appUserRepository.save(user);

        String baseVpa = (registerRequest.getRequestedVpa() != null ? registerRequest.getRequestedVpa() : registerRequest.getName().toLowerCase());
        if (!baseVpa.endsWith("@upi")) {
            baseVpa += "@upi";
        }

        Account account = Account.builder()
                .vpa(baseVpa)
                .balance(BigDecimal.valueOf(registerRequest.getInitialBalance()))
                .user(user)
                .isActive(true)
                .build();
        accountRepository.save(account);

        user.setAccount(account);
        return appUserRepository.save(user);
    }
}