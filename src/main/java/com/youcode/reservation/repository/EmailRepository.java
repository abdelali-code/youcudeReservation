package com.youcode.reservation.repository;

import com.youcode.reservation.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
    boolean existsByEmail(String email);
}
