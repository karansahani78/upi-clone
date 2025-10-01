package com.karan.upi_clone.service;

import com.karan.upi_clone.dto.RegisterRequest;
import com.karan.upi_clone.model.AppUser;

public interface AuthService {

        AppUser registerUser(RegisterRequest registerRequest);
    }