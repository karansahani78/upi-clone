package com.karan.upi_clone.controller;

import com.karan.upi_clone.dto.RegisterRequest;
import com.karan.upi_clone.model.AppUser;
import com.karan.upi_clone.service.AuthService;
import com.karan.upi_clone.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public AppUser register(@RequestBody RegisterRequest req) {
        return authService.registerUser(req);
    }

    // ✅ Fix: accept JSON instead of query params
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest) {
        String mobile = loginRequest.get("mobile");
        String password = loginRequest.get("password");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(mobile, password));

        String token = jwtUtil.generateToken(mobile);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
