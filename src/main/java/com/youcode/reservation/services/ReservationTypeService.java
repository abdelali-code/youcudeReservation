package com.youcode.reservation.services;


import com.youcode.reservation.model.ReservationType;
import com.youcode.reservation.repository.ReservationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationTypeService {
    @Autowired
    private ReservationTypeRepository reservationTypeRepository;

    public ReservationType addReservationType(ReservationType reservationType) {
        return reservationTypeRepository.save(reservationType);
    }

    /** get all the reservation type */
    /** void -> list<ReservationType> */
    public List<ReservationType> getAllReservationType() {
        return reservationTypeRepository.findAll();
    }

    public ReservationType updateReservationType(long id) {
        ReservationType reservationType = reservationTypeRepository.getOne(id);
        reservationType.setName("another name");
        return reservationTypeRepository.save(reservationType);
    }
}
