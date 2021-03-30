package com.youcode.reservation.controller;


import com.youcode.reservation.model.*;
import com.youcode.reservation.repository.ReservationRepository;
import com.youcode.reservation.repository.ReservationTypeRepository;
import com.youcode.reservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.DayOfWeek;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ReservationTypeRepository reservationTypeRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

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
    public String addReservation(@ModelAttribute("reservation") Reservation reservation, RedirectAttributes redirAttrs) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         if (!(authentication instanceof AnonymousAuthenticationToken)) {
             CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
             boolean res = reservationService.addReservation(reservation, currentUser);
             if (res) {
                 redirAttrs.addFlashAttribute("added", "you reservation is added successfuly");
             }else {
                 System.out.println("here reservation already exist");
                 redirAttrs.addFlashAttribute("reservationExist", "reservation already exist");
             }
             return "redirect:/";
         }
         return "redirect:/login?msg=you are not log in";
     }
}
