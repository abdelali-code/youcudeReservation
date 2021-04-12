package com.youcode.reservation.repository;

import com.youcode.reservation.model.Reservation;
import com.youcode.reservation.model.ReservationType;
import com.youcode.reservation.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByIdIn(List<Long> ids);
    void deleteAllByIdIn(List<Long> ids);

    //List<Reservation> findAllByAcceptedAAndDateLessThan(boolean isAccepter, Date date);

    Reservation findByDateAndAndReservationType(Date date, ReservationType reservationType);
    @Modifying
    @Query("UPDATE reservation r SET r.isAccepted = true WHERE r.id = :resId")
    void updateReservation(@Param("resId") long resId);

    List<Reservation> findAllByIsAcceptedAndDate(boolean is_accepted, Date date);

    @Query(nativeQuery = true, value = "SELECT * FROM reservation r WHERE r.reservationType.id = :resTypeId ORDER BY r.user.num_presence LIMIT r.reservationType.size")
    List<Reservation> selectAllByReservationType(@Param("resTypeId") long resTypeId);




    /** why do we need is this
     *
     * get List of reservation based on Reservation type id and a date
     * this list should be sorted by reservation.user.num_presence
     * and finally we should limit this list to the reservationType.size
     *
     * */
    /**  id(reservation_Type) -> List<Reservation> */
    List<Reservation> findByReservationTypeIdAndDate(long id, Date date, Sort sort);

    /**  get reservation less than the today date
     * @param date*/
    List<Reservation> getAllByDateEquals(Date date);


    List<Reservation> findAllByDateLessThan(Date date);
    List<Reservation> findAllByDateGreaterThanOrDateEquals(Date date, Date date2);

    Reservation findByDateAndUserAndReservationType(Date date, User user, ReservationType reservationType);
}
