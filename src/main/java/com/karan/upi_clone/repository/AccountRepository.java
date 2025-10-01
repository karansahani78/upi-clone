package com.karan.upi_clone.repository;

import com.karan.upi_clone.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByVpa(String vpa);
}
