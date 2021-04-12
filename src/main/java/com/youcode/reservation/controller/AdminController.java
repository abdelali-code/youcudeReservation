package com.youcode.reservation.controller;


import com.youcode.reservation.services.ReservationService;
import com.youcode.reservation.services.ReservationTypeService;
import com.youcode.reservation.services.TempUserService;
import com.youcode.reservation.services.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin-dashboard")
public class AdminController {

    @Autowired
    private TempUserService tempUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationTypeService reservationTypeService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public String getAdmin(Model model) {
        model.addAttribute("currentActivepage", 1);
        model.addAttribute("tempUser", tempUserService.getAllNotEvalualedUser());
        return "admin";
    }


    @GetMapping("/users")
    public String getAllApprennt(Model model) {
        model.addAttribute("currentActivepage", 3);
        model.addAttribute("allUsers", userService.getAllApprenant());
        return "adminAllApprenant";
    }

    @PostMapping("/users/delete")
    public String removeApprenant(@RequestParam("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin-dashboard/users";
    }

    @GetMapping("/all-reseravations")
    public String getAllReservation(Model model) {
        model.addAttribute("allRes", reservationService.getAllLessThanToday());
        model.addAttribute("currentActivepage", 0);
        return "adminAllReservation";
    }

    @GetMapping("/users-refuser")
    public String getRefuserUser(Model model) {
        model.addAttribute("currentActivepage", 4);
        model.addAttribute("tempUser", tempUserService.getAllEvalualedUserAndRefused());
        return "adminRefuserUser";
    }


    /** to handle users acceptation */
    @PostMapping("/accepter")
    public String accepterUser(@RequestParam("id") long id) {
        System.out.println(id);
        tempUserService.accepterUser(id);
        return "redirect:/admin-dashboard";
    }

    /** to handle users deleting */
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") long id) {
        System.out.println(id);
        tempUserService.deleteUserById(id);
        return "redirect:/admin-dashboard/users-refuser";
    }

    /** refuse a users */
    @PostMapping("/refuser")
    public String refuserrUser(@RequestParam("id") long id) {
        System.out.println(id);
        tempUserService.refuserUser(id);
        return "redirect:/admin-dashboard";
    }


//    reservation
    @GetMapping("/current-reservation")
    public String getTodayReservation(Model model) {
        model.addAttribute("currentActivepage", 2);
        model.addAttribute("todayReservation", reservationService.getAllResGreaterOrEqualToday());
        return "todayReservation";
    }


    @PostMapping("/accepter-reservation")
    public String accepterReservation(@RequestParam("id") long id) {
        reservationService.accepterReservation(id);
        return "redirect:/admin-dashboard/current-reservation";
    }
//    end reservation

    @PostMapping("/delete-reservation")
    public String removeReservation(@RequestParam("id") long id) {
        reservationService.removeReservation(id);
        return "redirect:/admin-dashboard/current-reservation";
    }



}
