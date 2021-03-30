package com.youcode.reservation.controller;

import com.youcode.reservation.model.User;
import com.youcode.reservation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;




@Controller
@RequestMapping("/singup")
public class SingupController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getSingup(@ModelAttribute("user") User user) {
        return "singup";
    }

    @PostMapping
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        /** check for erros */
        if (bindingResult.hasErrors()) {
            return "singup";
        }
        /** check if confirmed password is match password */
        String result = userService.ajouterNewTempUser(user, bindingResult);
        if (result == null) {
            return "redirect:/login";
        }else {
            return result;
        }
    }
}
