package com.youcode.reservation.controller;

import com.youcode.reservation.model.TempUser;
import com.youcode.reservation.model.User;
import com.youcode.reservation.services.TempUserService;
import com.youcode.reservation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/singup")
public class SingupController {

    @Autowired
    private TempUserService tempUserService;

    @GetMapping
    public String getSingup(@ModelAttribute("user") TempUser tempUser) {
        return "singup";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") TempUser tempUser) {
        System.out.println(tempUser.getPassword());
        /** check for erros */
        tempUserService.addTempUser(tempUser);
        return "redirect:/singup";
    }
}
