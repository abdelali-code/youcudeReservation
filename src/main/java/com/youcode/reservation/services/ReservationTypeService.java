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

    public ReservationType updateReservationType(ReservationType reservationType) {
//        reservationTypeRepository.updateReservation(reservationType.getId(), reservationType.getName(), reservationType.getSize());
//        return reservationTypeRepository.findById(reservationType.getId()).get();
        ReservationType reservationTypeTarget = reservationTypeRepository.findById(reservationType.getId()).get();
        if (reservationTypeTarget != null) {
            reservationTypeTarget.setSize(reservationType.getSize());
            reservationTypeTarget.setName(reservationType.getName());
            reservationTypeRepository.saveAndFlush(reservationTypeTarget);
        }
        return reservationTypeTarget;
    }

    public void deleteReservationType(List<Long> ids) {
        reservationTypeRepository.deleteAllByIdIn(ids);
    }


    public List<ReservationType> getAll() {
        return reservationTypeRepository.findAll();
    }

    public void deleteReservationById(long id) {
        reservationTypeRepository.deleteById(id);
    }


    public ReservationType getReservationType(long id) {
        return reservationTypeRepository.findById(id).get();
    }
}
