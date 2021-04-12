//package com.youcode.reservation.rest;
//
//import com.youcode.reservation.model.TempUser;
//import com.youcode.reservation.services.TempUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000")
//public class WaitUserController {
//
//    @Autowired
//    private TempUserService tempUserService;
//
//    @CrossOrigin(origins = "http://localhost:8080")
//    @GetMapping("/waitUsers")
//    public List<TempUser> getAllAttendsUser() {
//        System.out.println("get all the user");
//        return tempUserService.getALlTempUser();
//    }
//
//    @CrossOrigin(origins = "http://localhost:8080")
//    @DeleteMapping("/waitUsers")
//    public boolean deleteUsers(@RequestBody List<Long> ids) {
//        System.out.println("delete user is invoked");
//        for (long i: ids) {
//            System.out.println(i);
//        }
//        tempUserService.deleteUsersByIds(ids);
//        return true;
//    }
//
//    /** accepte users */
//    @CrossOrigin(origins = "http://localhost:8080")
//    @PostMapping("/waitUsers")
//    public boolean accepterUserByIdIn(@RequestBody List<Long> ids) {
//        System.out.println(ids);
//        tempUserService.accepterUsersByIds(ids);
//        return true;
//    }
//}
