package com.youcode.reservation.controller;


import com.youcode.reservation.model.CustomUserDetails;
import com.youcode.reservation.model.User;
import com.youcode.reservation.repository.UserRepository;
import com.youcode.reservation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class USerProfile {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getProFile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
        User user = userRepository.findById(currentUser.getUser().getId()).get();
        model.addAttribute("user", user);
        return "profile";
    }
}
