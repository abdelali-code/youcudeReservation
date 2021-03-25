package com.youcode.reservation.controller;


import com.youcode.reservation.model.TempUser;
import com.youcode.reservation.services.TempUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestAdminController {

    @Autowired
    private TempUserService tempUserService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/waitUsers")
    public List<TempUser> getAllAttendsUser() {
        return tempUserService.getALlTempUser();
    }

    /** accepte user */
    public boolean accepterUser(long id) {
        return false;
    }
}
