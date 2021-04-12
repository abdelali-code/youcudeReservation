//package com.youcode.reservation.rest;
//
//import com.youcode.reservation.model.ReservationType;
//import com.youcode.reservation.repository.ReservationTypeRepository;
//import com.youcode.reservation.services.ReservationTypeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000")
//
//public class RestReservationTypeController {
//
//    @Autowired
//    private ReservationTypeRepository reservationTypeRepository;
//
//    @Autowired
//    private ReservationTypeService reservationTypeService;
//
//    /** get all reservation type */
//    @CrossOrigin(origins = "http://localhost:8080")
//    @GetMapping("/reservationType")
//    public List<ReservationType> getAllResType() {
//        return reservationTypeRepository.findAll();
//    }
//
//
//    /** add reservation type */
//    @CrossOrigin(origins = "http://localhost:8080")
//    @PostMapping("/reservationType")
//    public ReservationType addReservationType(@RequestBody ReservationType reservationType) {
//        System.out.println(reservationType.getName());
//        return reservationTypeRepository.save(reservationType);
//    }
//
//    /** update reservation type */
//    @CrossOrigin(origins = "http://localhost:8080")
//    @Transactional
//    @PutMapping("/reservationType")
//    public void updateReservationType(@RequestBody ReservationType reservationType) {
//        reservationTypeService.updateReservationType(reservationType);
//    }
//
//    /** delete list of reservation type */
//    @CrossOrigin(origins = "http://localhost:8080")
//    @Transactional
//    @DeleteMapping("/reservationType")
//    public boolean deleteReservationType(@RequestBody List<Long> ids) {
//        reservationTypeService.deleteReservationType(ids);
//        return true;
//    }
//}
