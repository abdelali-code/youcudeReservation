package com.youcode.reservation.services;


import com.youcode.reservation.model.CustomUserDetails;
import com.youcode.reservation.model.Reservation;
import com.youcode.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public boolean addReservation(Reservation reservation, CustomUserDetails currentUser) {
            LocalDate localDate = LocalDate.now().with(TemporalAdjusters.next(reservation.getDay()));
            Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            reservation.setDate(date1);
            reservation.setUser(currentUser.getUser());
            Reservation isReservationExist = reservationRepository.findByDateAndAndReservationType(date1, reservation.getReservationType());
            /** if isReservationExist equal null */
            if (isReservationExist == null) {
                reservationRepository.save(reservation);
                return true;
            }
            else {
                return false;
            }
    }
}
