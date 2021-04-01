package com.youcode.reservation.controller;


import com.youcode.reservation.model.CustomUserDetails;
import com.youcode.reservation.model.Notification;
import com.youcode.reservation.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Access;
import java.util.List;

@Controller
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;
    @GetMapping
    public String getNotification(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null || !(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
            List<Notification> notificationList = notificationRepository.findAllByUserId(currentUserDetails.getUser().getId());
            model.addAttribute("notifications", notificationList);
            return "notification";
        }
        return "redirect:/login";
    }
}
