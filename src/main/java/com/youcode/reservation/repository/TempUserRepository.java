package com.youcode.reservation.repository;

import com.youcode.reservation.model.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempUserRepository extends JpaRepository<TempUser, Long> {
}
