package com.youcode.reservation.controller;


import com.youcode.reservation.model.User;
import com.youcode.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;
     @GetMapping("/")
    public String home(Model model) {
         model.addAttribute("home", "this is the home page");
         User user = new User();
         user.setEmail("email");

         userRepository.save(user);
         return "home";
     }
}
