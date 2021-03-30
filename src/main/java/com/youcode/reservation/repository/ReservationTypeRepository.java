package com.youcode.reservation.repository;

import com.youcode.reservation.model.ReservationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationTypeRepository extends JpaRepository<ReservationType, Long> {

    void deleteAllByIdIn(List<Long> ids);
    @Modifying
    @Query("UPDATE reservation_type r SET r.name = :name, r.size=:size WHERE r.id = :resId")
    void updateReservation(@Param("resId") long resId, @Param("name") String name, @Param("size") int size);
}
