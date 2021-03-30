package com.youcode.reservation.rest;


import com.youcode.reservation.model.Reservation;
import com.youcode.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/api")
public class RestReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    /** get all reservation */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/reservation")
    public List<Reservation> getALlReservation() {
        return reservationRepository.findAll();
    }


    /** accepter list of the reservation */
    /** update reservation is_accepted from false to true */
    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.PUT)
    @PutMapping("/reservation")
    public void accpterListResrvation(@RequestBody List<Long> ids) {
        System.out.println(ids.toString());
        System.out.println("accept method is invoked");
        //List<Reservation> reservationList = reservationRepository.findAllByIdIn(ids);
        for (long id : ids) {
            reservationRepository.updateReservation(id);
        }
    }

    /** delete list of the reservation */
    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.DELETE)
    @DeleteMapping("/reservation")
    public boolean deleteListResrvation(@RequestBody List<Long> ids) {
        System.out.println("ids " + ids.toString());
        System.out.println("delete method is invoked ....");
        reservationRepository.deleteAllByIdIn(ids);
        return true;
    }
}
