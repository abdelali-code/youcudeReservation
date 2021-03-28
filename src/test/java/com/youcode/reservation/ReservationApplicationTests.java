package com.youcode.reservation;

import com.youcode.reservation.services.TempUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ReservationApplicationTests {

    @Autowired
    private TempUserService tempUserService;

    @Test
    void contextLoads() {
    }

    @Test
    void deleteUseListtest() {
        List<Long> list = new ArrayList<>();
        list.add(3L);
        list.add(4L);
        tempUserService.deleteUsersByIds(list);
    }


    /** password length */
    @Test
    void passwordLen() {
        System.out.println("$2a$10$eTmQR/IPlrHB1hCUrFhYxuFcVbW7SX3hic7bOTUIcLDd4rl9NmtQq".length());
    }

}
