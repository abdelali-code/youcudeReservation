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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.persistence.Access;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping
    public List<Notification> getNotification(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null || !(authentication instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails currentUserDetails = (CustomUserDetails) authentication.getPrincipal();
            List<Notification> notificationList = notificationRepository.findAllByUserId(currentUserDetails.getUser().getId());
//            model.addAttribute("notifications", notificationList);
//            return "notification";
            return notificationList;
        }
        else throw new RuntimeException("you are not authorised");
    }

}
