package com.youcode.reservation.rest;

import com.youcode.reservation.model.TempUser;
import com.youcode.reservation.services.TempUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WaitUserController {

    @Autowired
    private TempUserService tempUserService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/waitUsers")
    public List<TempUser> getAllAttendsUser() {
        return tempUserService.getALlTempUser();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/waitUsers")
    public boolean deleteUsers(@RequestBody List<Long> ids) {
        System.out.println(ids.size());
        System.out.println(ids.toString());
        for (long i: ids) {
            System.out.println(i);
        }
        tempUserService.deleteUsersByIds(ids);
        return true;
    }

    /** accepte users */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/waitUsers")
    public boolean accepterUserByIdIn(@RequestBody List<Long> ids) {
        tempUserService.accepterUsersByIds(ids);
        return true;
    }
}
