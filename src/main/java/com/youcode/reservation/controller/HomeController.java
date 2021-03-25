package com.youcode.reservation.controller;


import com.youcode.reservation.model.Day;
import com.youcode.reservation.model.Reservation;
import com.youcode.reservation.model.ReservationType;
import com.youcode.reservation.model.User;
import com.youcode.reservation.repository.ReservationTypeRepository;
import com.youcode.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ReservationTypeRepository reservationTypeRepository;

     @GetMapping("/")
    public String home(Model model) {
         Reservation reservation = new Reservation();
        List<ReservationType> reservationTypeList = reservationTypeRepository.findAll();
        model.addAttribute("reser_list", reservationTypeList);
        model.addAttribute("reservation", reservation);
        model.addAttribute("days", DayOfWeek.values());
         return "home";
     }

     @PostMapping("/addReservation")
    public String addReservation(@ModelAttribute("reservation") Reservation reservation) {
         System.out.println(reservation.getDay());
         System.out.println(reservation.getReservationType());
         LocalDate localDate = LocalDate.now().with(TemporalAdjusters.next(reservation.getDay()));
         Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
         System.out.println(date1);
         return "redirect:/";
     }
}
