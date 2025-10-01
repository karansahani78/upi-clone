package com.karan.upi_clone.service;

import com.karan.upi_clone.model.AppUser;
import com.karan.upi_clone.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByMobile(mobile)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with mobile: " + mobile));

        return User.builder()
                .username(user.getMobile())  // login with mobile
                .password(user.getPassword())
                .roles("USER")  // default role
                .build();
    }
}
