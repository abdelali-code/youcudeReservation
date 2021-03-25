package com.youcode.reservation.services;

import com.youcode.reservation.model.TempUser;
import com.youcode.reservation.model.User;
import com.youcode.reservation.repository.TempUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempUserService {
    @Autowired
    private TempUserRepository tempUserRepository;

    @Autowired
    private UserService userService;

    public TempUser addTempUser(TempUser tempUser) {
        return tempUserRepository.save(tempUser);
    }

    // get all temporary users
    public List<TempUser> getALlTempUser() {
        return tempUserRepository.findAll();
    }

    /** get user by id */
    public TempUser getTempUserById(long id) {
        return tempUserRepository.getOne(id);
    }

    /** update user status */
    /** accepter user */
    public User validateUser(TempUser tempUser) {
        User user = tempUser.createUser();
        /** now remove this user from temporary users and add it to the users table */
        tempUserRepository.deleteById(tempUser.getId());
        return userService.addUser(user);
    }

    /** refuser a user */
    public void refuserTempUser(TempUser tempUser) {
        tempUserRepository.deleteById(tempUser.getId());
    }
}
