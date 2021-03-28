package com.youcode.reservation.controller;

import com.youcode.reservation.model.TempUser;
import com.youcode.reservation.model.User;
import com.youcode.reservation.services.TempUserService;
import com.youcode.reservation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    private TempUserService tempUserService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getSingup(@ModelAttribute("user") TempUser tempUser) {
        return "singup";
    }

    @PostMapping
    public String addUser(@Valid @ModelAttribute("user") TempUser tempUser, BindingResult bindingResult) {
        /** check for erros */
        if (bindingResult.hasErrors()) {
            return "singup";
        }
        /** check if confirmed password is match password */
        if (!tempUser.isPasswordConfirmed()) {
            bindingResult.rejectValue("confirmPassword", "doesNotMatch","password does not match");
            return "singup";
        }
        try {
            tempUserService.addTempUser(tempUser);
            userService.addUser(tempUser.createUser());
        }catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("email", "emailExist","email already exist");
            return "singup";
        }

        return "redirect:/login?msg=please confirm your account";
    }
}
