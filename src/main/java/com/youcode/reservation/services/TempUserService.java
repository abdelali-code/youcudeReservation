package com.youcode.reservation.services;

import com.youcode.reservation.model.TempUser;
import com.youcode.reservation.repository.TempUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempUserService {
    @Autowired
    private TempUserRepository tempUserRepository;

    public TempUser addTempUser(TempUser tempUser) {
        return tempUserRepository.save(tempUser);
    }
}
