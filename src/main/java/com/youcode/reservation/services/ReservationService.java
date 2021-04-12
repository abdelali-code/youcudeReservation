package com.youcode.reservation.services;

import com.youcode.reservation.model.CustomUserDetails;
import com.youcode.reservation.model.Notification;
import com.youcode.reservation.model.Reservation;
import com.youcode.reservation.model.ReservationType;
import com.youcode.reservation.repository.NotificationRepository;
import com.youcode.reservation.repository.ReservationRepository;
import com.youcode.reservation.repository.ReservationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationTypeRepository reservationTypeRepository;


    @Autowired
    private NotificationRepository notificationRepository;

    public boolean addReservation(Reservation reservation, CustomUserDetails currentUser) {
            LocalDate localDate = LocalDate.now().with(TemporalAdjusters.next(reservation.getDay()));
            Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            reservation.setDate(date1);
            reservation.setUser(currentUser.getUser());
            //Reservation isReservationExist = reservationRepository.findByDateAndAndReservationType(date1, reservation.getReservationType());
            Reservation isReservationExist = reservationRepository.findByDateAndUserAndReservationType(date1, reservation.getUser(), reservation.getReservationType());
        /** if isReservationExist equal null */
            if (isReservationExist == null) {
                reservationRepository.save(reservation);
                return true;
            }
            else {
                return false;
            }
    }

    /** get reservation by role */
    /** get all reservation type and extract List<Reservation> based on reservation type
     * select the top reservation order Incremental by the number of presence of the user
     * */
    @Transactional
    public List<List<Reservation>> getReservationByReservationType() {
        LocalDate localDate = LocalDate.now();
        LocalDate tempLocaleDate = LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth() + 1);
        Date date = Date.from(tempLocaleDate.atStartOfDay(ZoneId.of( "Africa/Casablanca" )).toInstant());

        List<ReservationType> reservationTypes = reservationTypeRepository.findAll();
        List<List<Reservation>> listOfListOfReservation = new ArrayList<>();

        for (ReservationType type : reservationTypes) {
            List<Reservation> reservationList  = reservationRepository.findByReservationTypeIdAndDate(type.getId(), date, Sort.by(Sort.Direction.ASC, "user.numPresence").ascending());
            if (reservationList.size() > type.getSize()) {
                listOfListOfReservation.add(reservationList.subList(0, type.getSize()));
            }else {
                listOfListOfReservation.add(reservationList);
            }
        }
        /** update the reservatin table to make the selected reservation accepter */
        makeReservationsAccepted(listOfListOfReservation);

        return listOfListOfReservation;
    }

    /**
     * List<List<Reservation>> -> void
     * update is_accepted field of reservation from the false to true
     * */
    private void makeReservationsAccepted(List<List<Reservation>> listListReservation) {
        for (List<Reservation> reservationList : listListReservation) {
            for (Reservation reservation : reservationList) {
                reservation.setAccepted(true);
                reservation.getUser().setNumPresence(reservation.getUser().getNumPresence() + 1);
                reservationRepository.saveAndFlush(reservation);
                /** store a notification for the current user to tell that his/her reservation is accepted */
                String message = "Your reservation for " + reservation.getDate() + " is accepted for " + reservation.getReservationType().getName();
                Notification notification = new Notification(message, reservation.getUser());
                notificationRepository.save(notification);
            }
        }
    }

    /** accepter reservation */
    /** switch is_accepter reservation field from false to true */
    public void updateReservation(long id) {
        reservationRepository.updateReservation(id);
    }

    /** delete a list of reservation by its ids */
    public void deleteAllByIds(List<Long> ids) {
        reservationRepository.deleteAllByIdIn(ids);
    }

    /** find all reservation */
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    /** find all greater than today historique */
    public List<Reservation> getAllLessThanToday() {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return reservationRepository.findAllByDateLessThan(date);
    }

    /** get today reservation */
    public List<Reservation> getTodayReservation() {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return reservationRepository.getAllByDateEquals(date);
    }

    /** find all reservation that it's date greater than or equal to today date */
    public List<Reservation> getAllResGreaterOrEqualToday() {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return reservationRepository.findAllByDateGreaterThanOrDateEquals(date, date);
    }

    /** accepter a reservation */
    @Transactional
    public void accepterReservation(long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        if (reservation != null) {
            reservation.setAccepted(true);
            reservationRepository.saveAndFlush(reservation);
            reservation.getUser().setNumPresence(reservation.getUser().getNumPresence()+1);
            String message = "Your reservation for " + reservation.getDate() + " is accepted for " + reservation.getReservationType().getName();
            Notification notification = new Notification(message, reservation.getUser());
            notificationRepository.save(notification);
        }
    }

    public void removeReservation(long id) {
        reservationRepository.deleteById(id);
    }
}
