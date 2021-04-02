package com.youcode.reservation;

import com.youcode.reservation.controller.AcceptedReservationController;
import com.youcode.reservation.gravatarGenerator.Gravatar;
import com.youcode.reservation.repository.EmailRepository;
import com.youcode.reservation.services.ReservationService;
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

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AcceptedReservationController acceptedReservationController;

    @Autowired
    private Gravatar gravatar;

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

    @Test
    void emailExist() {
        assert emailRepository.existsByEmail("test@gmail.com") == true;
    }
    @Test
    void emailDoesNotExist() {
        assert emailRepository.existsByEmail("notValidEmail@gmail.com") == false;
    }

    @Test
    void setAcceptedReservationControllertest () {
        acceptedReservationController.accptedReservation(null);
    }

    @Test
    void getReservationByReservationType() {
        reservationService.getReservationByReservationType();
    }

    @Test
    void testGravatar() {
        String email = "exemple@gmail.com";
        String hash = gravatar.md5Hex(email);
        //923d10bc97028030e8e67e7db62658d1
        //923d10bc97028030e8e67e7db62658d1

        //https://www.gravatar.com/avatar/923d10bc97028030e8e67e7db62658d1?d=robohash
        System.out.println(hash);
    }
}
