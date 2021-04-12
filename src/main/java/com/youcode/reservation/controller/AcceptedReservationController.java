//package com.youcode.reservation.controller;
//
//import com.youcode.reservation.model.Reservation;
//import com.youcode.reservation.repository.ReservationRepository;
//import com.youcode.reservation.services.ReservationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.List;
//
//@Controller
//public class AcceptedReservationController {
//
//    @Autowired
//    private ReservationRepository reservationRepository;
//
//    @Autowired
//    private ReservationService reservationService;
//
//    @GetMapping("/accepted-reservation")
//    public String accptedReservation(Model model) {
//        LocalDate localDate = LocalDate.now();
//        Date date = Date.from(localDate.atStartOfDay(ZoneId.of( "Africa/Casablanca" )).toInstant());
//
//        System.out.println("date " +  date);
////        List<Reservation> reservationList = reservationRepository.findAllByDate(date);
//        //List<List<Reservation>> listListReservation = reservationService.getReservationByReservationType();
//        List<Reservation> reservationList = reservationRepository.findAllByIsAcceptedAndDate(true, date);
//        //model.addAttribute("listOffAllReservation", reservationList);
//        model.addAttribute("reservation", reservationList);
//        model.addAttribute("today", localDate);
//        return "acceptedReservation";
//    }
//
//}
