package com.youcode.reservation.rest;


import com.youcode.reservation.model.Reservation;
import com.youcode.reservation.repository.ReservationRepository;
import com.youcode.reservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api")
public class RestReservationController {

    @Autowired
    private ReservationService reservationService;

    /** get all reservation */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/reservation")
    public List<Reservation> getALlReservation() {
        return reservationService.getAll();
    }


    /** accepter list of the reservation */
    /** update reservation is_accepted from false to true */
    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.PUT)
    @PutMapping("/reservation")
    public void accpterListResrvation(@RequestBody List<Long> ids) {
        //List<Reservation> reservationList = reservationRepository.findAllByIdIn(ids);
        for (long id : ids) {
            reservationService.updateReservation(id);
        }
    }

    /** delete list of the reservation */
    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.DELETE)
    @DeleteMapping("/reservation")
    public boolean deleteListResrvation(@RequestBody List<Long> ids) {
        reservationService.deleteAllByIds(ids);
        return true;
    }
}
