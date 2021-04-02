package com.youcode.reservation.services;


import com.youcode.reservation.gravatarGenerator.Gravatar;
import com.youcode.reservation.model.Email;
import com.youcode.reservation.model.TempUser;
import com.youcode.reservation.model.User;
import com.youcode.reservation.repository.EmailRepository;
import com.youcode.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.persistence.Access;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailRepository emailRepository;

    public User addUser(User user) {
        /** crybt password */
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void saveAllUserIn(List<User> users) {
        /** generate gravatar */
        for (User user: users) {
            String gravatar = Gravatar.md5Hex(user.getEmail());
            user.setGravatar(gravatar);
        }
        userRepository.saveAll(users);
    }

    private boolean checkValidationOfEmail(String email) {
        return emailRepository.existsByEmail(email);
    }























    /** store temperary user in temp_user database table */
    public String ajouterNewTempUser(User user, BindingResult bindingResult) {
        /** check if email is a valid youcode email */
        boolean isEmailValid = checkValidationOfEmail(user.getEmail());
        if (!isEmailValid) {
            bindingResult.rejectValue("email", "emailIsNotValid","is not a valid youcoude email");
            return "singup";
        }
        /** check if confirmed password is match password */
        if (!user.isPasswordConfirmed()) {
            bindingResult.rejectValue("confirmPassword", "doesNotMatch","password does not match");
            return "singup";
        }
        try {
            addUser(user);
        }
        catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("email", "emailExist","email already exist");
            return "singup";
        }
        return null;
    }




}
