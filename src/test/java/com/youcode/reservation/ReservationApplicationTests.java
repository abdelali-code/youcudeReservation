//package com.youcode.reservation;
//
////import com.youcode.reservation.controller.AcceptedReservationController;
//import com.youcode.reservation.gravatarGenerator.Gravatar;
//import com.youcode.reservation.model.Reservation;
//import com.youcode.reservation.model.User;
//import com.youcode.reservation.repository.EmailRepository;
//import com.youcode.reservation.repository.ReservationRepository;
//import com.youcode.reservation.repository.UserRepository;
//import com.youcode.reservation.services.ReservationService;
//import com.youcode.reservation.services.TempUserService;
//import com.youcode.reservation.services.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@SpringBootTest
//class ReservationApplicationTests {
//
//    @Autowired
//    private TempUserService tempUserService;
//
//    @Autowired
//    private EmailRepository emailRepository;
//
//    @Autowired
//    private ReservationService reservationService;
////
////    @Autowired
////    private AcceptedReservationController acceptedReservationController;
//
//    @Autowired
//    private Gravatar gravatar;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
//
//    @Autowired
//    private ReservationRepository reservationRepository;
//
//
//    @Test
//    public void getReservationLessThanTodayDate() {
//        LocalDate localDate =  LocalDate.now();
//        List<Reservation> reservationList = reservationRepository.getAllByDateEquals(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        for (Reservation reservation : reservationList) {
//            System.out.println(reservation.getDay());
//        }
//    }
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void deleteUseListtest() {
//        List<Long> list = new ArrayList<>();
//        list.add(3L);
//        list.add(4L);
//        tempUserService.deleteUsersByIds(list);
//    }
//
//
//    /** password length */
//    @Test
//    void passwordLen() {
//        System.out.println("$2a$10$eTmQR/IPlrHB1hCUrFhYxuFcVbW7SX3hic7bOTUIcLDd4rl9NmtQq".length());
//    }
//
//    @Test
//    void emailExist() {
//        assert emailRepository.existsByEmail("test@gmail.com") == true;
//    }
//    @Test
//    void emailDoesNotExist() {
//        assert emailRepository.existsByEmail("notValidEmail@gmail.com") == false;
//    }
//
//    @Test
//    void setAcceptedReservationControllertest () {
//        acceptedReservationController.accptedReservation(null);
//    }
//
//    @Test
//    void getReservationByReservationType() {
//        reservationService.getReservationByReservationType();
//    }
//
//    @Test
//    void addUser() {
//        User user = new User();
//        user.setPassword("password");
//        user.setEmail("admin@gmail.com");
//        user.setFirstname("firstname");
//        user.setLastname("lastname");
//        user.setGravatar("gravatar");
//        userService.addUser(user);
//    }
//    @Test
//    void testGravatar() {
//        String email = "exemple@gmail.com";
//        String hash = gravatar.md5Hex(email);
//        //923d10bc97028030e8e67e7db62658d1
//        //923d10bc97028030e8e67e7db62658d1
//
//        //https://www.gravatar.com/avatar/923d10bc97028030e8e67e7db62658d1pa
//        System.out.println(hash);
//    }
//
//    @Test
//    void getApprenant() {
//        List<User> users = userRepository.findAllByRoles_Name("apprenant");
//        System.out.println("result is here");
//        for (User user: users) {
//            System.out.println(user.getLastname());
//        }
//    }
//}
