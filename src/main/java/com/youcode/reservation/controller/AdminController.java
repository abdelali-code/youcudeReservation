package com.youcode.reservation.controller;


import com.youcode.reservation.model.ReservationType;
import com.youcode.reservation.repository.ReservationTypeRepository;
import com.youcode.reservation.services.ReservationTypeService;
import com.youcode.reservation.services.TempUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TempUserService tempUserService;

    @Autowired
    private ReservationTypeService reservationTypeService;

    @GetMapping
    public String getAdmin(Model model) {

        ReservationType reservationType = new ReservationType();
        model.addAttribute("reservation_type", reservationType);
        model.addAttribute("tempUser", tempUserService.getALlTempUser());
        model.addAttribute("reservationTypeList", reservationTypeService.getAllReservationType());
        return "admin";
    }

    @PostMapping
    public String postAdmin(@ModelAttribute("reservation_type") ReservationType reservationType,
                            @RequestParam("action") String action) {
        /** mean that the user want to add new reservation type */
        if (action.equals("addType")) {
            handleAddReservationType(reservationType);
        }
        else if (action.equals("accepter")) {
            System.out.println("accepeter");
        }
        else if (action.equals("refuser")) {
            System.out.println("refuser");
        }
        return "redirect:/admin";
    }



    @PostMapping("/reservation-type")
    public void addReservationType(@ModelAttribute("reservation_type") ReservationType reservationType) {
        System.out.println(reservationType.getName());
    }

    /**
     * handle adding reservation type to database
     */
    private void handleAddReservationType(ReservationType reservationType) {
        reservationTypeService.addReservationType(reservationType);
    }

}
