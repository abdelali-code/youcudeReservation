package com.youcode.reservation.services;

import com.youcode.reservation.model.User;
import com.youcode.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetAccount {

    @Autowired
    private UserRepository userRepository;


    public void restAccountPassword(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            System.out.println("user does not exist");
        }else {
            System.out.println("email is sent");
        }
    }
}
