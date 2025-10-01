package com.karan.upi_clone.repository;

import com.karan.upi_clone.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
  Optional<AppUser>findByMobile(String mobile);
}
