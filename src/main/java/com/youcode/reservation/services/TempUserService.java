package com.youcode.reservation.services;

import com.youcode.reservation.model.TempUser;
import com.youcode.reservation.model.User;
import com.youcode.reservation.repository.TempUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TempUserService {
    @Autowired
    private TempUserRepository tempUserRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TempUser addTempUser(TempUser tempUser) {
        tempUser.setPassword(passwordEncoder.encode(tempUser.getPassword()));
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

    /** whe we refuse any user we delete it from the database */
    /** delete users by ids*/
    public void deleteUsersByIds(List<Long> ids) {
        tempUserRepository.deleteAllByIdIn(ids);
    }


    /**  accepter the user */
    @Transactional
    public void accepterUsersByIds(List<Long> ids) {
        List<TempUser> tempUserList = tempUserRepository.getAllByIdIn(ids);
        List<User> usersFrTempUser = getUserFromTempUser(tempUserList);
        userService.saveAllUserIn(usersFrTempUser);
        tempUserRepository.deleteAll(tempUserList);
    }


    /** convert a list of the temp user into a list of users */
    private List<User> getUserFromTempUser(List<TempUser> tempUserList) {
        List<User> userList = new ArrayList<>();
        for (TempUser tempUser: tempUserList) {
            userList.add(tempUser.createUser());
        }
        return userList;
    }
}
