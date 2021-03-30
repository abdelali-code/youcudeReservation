package com.youcode.reservation.repository;

import com.youcode.reservation.model.Reservation;
import com.youcode.reservation.model.ReservationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByIdIn(List<Long> ids);
    void deleteAllByIdIn(List<Long> ids);
    Reservation findByDateAndAndReservationType(Date date, ReservationType reservationType);
    @Modifying
    @Query("UPDATE reservation r SET r.is_accepted = true WHERE r.id = :resId")
    void updateReservation(@Param("resId") long resId);
}
