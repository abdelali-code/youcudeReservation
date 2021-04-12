package com.youcode.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.youcode.reservation.services.ResetAccount;

@Controller
@RequestMapping("/password")
public class ResetPassword {

    @Autowired
    private ResetAccount resetAccount;

    @GetMapping("/reset")
    public String getResetPasswordPage() {
        return "resetPassword";
    }

    @PostMapping("/reset")
    public String resetPassword(@RequestParam("resetEmail") String email, RedirectAttributes redirectAttributes) {
        System.out.println(email);
        resetAccount.restAccountPassword(email);
        redirectAttributes.addFlashAttribute("passResetMsg", "check your email to reset your password");

        return "redirect:/password/reset";
    }
}
