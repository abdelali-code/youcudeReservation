package com.youcode.reservation.TaskExecutor;

import com.youcode.reservation.repository.NotificationRepository;
import com.youcode.reservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class TaskExecutorReservator {
    @Autowired
    private ReservationService reservationService;


    @Scheduled(fixedDelay = 3600) //36000
    public void scheduleFixedDelayTask() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int hourNow = localDateTime.getHour();
        if (hourNow == 23) {
            reservationService.getReservationByReservationType();
        }
        /** after excuted for the first time we should sleep for a day of time */
    }
}
