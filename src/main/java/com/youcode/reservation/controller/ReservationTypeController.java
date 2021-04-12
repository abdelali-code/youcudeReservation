package com.youcode.reservation.controller;

import com.youcode.reservation.model.ReservationType;
import com.youcode.reservation.services.ReservationService;
import com.youcode.reservation.services.ReservationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/admin-dashboard/reservation-type")
public class ReservationTypeController {

    @Autowired
    private ReservationTypeService reservationTypeService;


    /* reservation type */
    @GetMapping
    public String getReservationType(Model model) {
        model.addAttribute("currentActivepage", 5);
        model.addAttribute("resType", reservationTypeService.getAllReservationType());
        model.addAttribute("reservationTypeEntity", new ReservationType());
        return "reservatonType";
    }

    @PostMapping("/add")
    public String addReservationType(@Valid @ModelAttribute("reservationTypeEntity") ReservationType reservationType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("errors");
            return "reservatonType";
        }
        reservationTypeService.addReservationType(reservationType);
        return "redirect:/admin-dashboard/reservation-type";
    }

    @PostMapping("/delete")
    public String deleteReservationType(@RequestParam("id") long id) {
        reservationTypeService.deleteReservationById(id);
        return "redirect:/admin-dashboard/reservation-type";
    }

    @PostMapping("/update")
    public String updateReservationType(@ModelAttribute("resType") ReservationType reservationType) {

        reservationTypeService.updateReservationType(reservationType);
        return "redirect:/admin-dashboard/reservation-type";
    }

    @GetMapping("/update/{id}")
    public String getupdateReservationTypeForm(Model model, @PathVariable("id") long id) {
        System.out.println(id);
        ReservationType reservationType = reservationTypeService.getReservationType(id);
        model.addAttribute("resType", reservationType);
        //reservationTypeService.updateReservationTypeById(id);
        return "updateReservationType";
        //return "redirect:/admin-dashboard/reservation-type";
    }
    /* end reservation type */
}
