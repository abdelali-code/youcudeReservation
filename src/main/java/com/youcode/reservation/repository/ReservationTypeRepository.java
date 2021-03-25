package com.youcode.reservation.repository;

import com.youcode.reservation.model.ReservationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationTypeRepository extends JpaRepository<ReservationType, Long> {
}
