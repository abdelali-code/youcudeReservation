package com.youcode.reservation.controller;

import com.youcode.reservation.model.TempUser;
import com.youcode.reservation.model.User;
import com.youcode.reservation.services.TempUserService;
import com.youcode.reservation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;




@Controller
@RequestMapping("/singup")
public class SingupController {

    @Autowired
    private UserService userService;

    @Autowired
    private TempUserService tempUserService;

    @GetMapping
    public String getSingup(@ModelAttribute("user") TempUser user) {
        return "singup";
    }

    @PostMapping
    public String addUser(@Valid @ModelAttribute("user") TempUser user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        /** check for erros */
        if (bindingResult.hasErrors()) {
            return "singup";
        }
        String result = tempUserService.ajouterNewTempUser(user, bindingResult);
        if (result == null) {
            redirectAttributes.addFlashAttribute("singupSucces", "you accound is added, wait for admin to accept you, then you can log in");
            return "redirect:/login";
        }else {
            return result;
        }
    }
}
