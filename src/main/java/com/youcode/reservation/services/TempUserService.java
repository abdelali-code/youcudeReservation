package com.youcode.reservation.services;

import com.youcode.reservation.model.Role;
import com.youcode.reservation.model.TempUser;
import com.youcode.reservation.model.User;
import com.youcode.reservation.repository.EmailRepository;
import com.youcode.reservation.repository.RoleRepository;
import com.youcode.reservation.repository.TempUserRepository;
import net.bytebuddy.matcher.ElementMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TempUserService {
    @Autowired
    private TempUserRepository tempUserRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailRepository emailRepository;


    @Autowired
    private MailSender mailSender;

    public TempUser addTempUser(TempUser tempUser) {
        tempUser.setPassword(passwordEncoder.encode(tempUser.getPassword()));
        return tempUserRepository.save(tempUser);
    }

    /** store temperary user in temp_user database table */
    public String ajouterNewTempUser(TempUser tempUser, BindingResult bindingResult) {
        /** check if email is a valid youcode email */
        boolean isEmailValid = checkValidationOfEmail(tempUser.getEmail());
        if (!isEmailValid) {
            bindingResult.rejectValue("email", "emailIsNotValid","is not a valid youcoude email");
            return "singup";
        }
        /** check if confirmed password is match password */
        if (!tempUser.isPasswordConfirmed()) {
            bindingResult.rejectValue("confirmPassword", "doesNotMatch","password does not match");
            return "singup";
        }
        try {
            addTempUser(tempUser);
        }
        catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("email", "emailExist","email already exist");
            return "singup";
        }
        return null;
    }

    private boolean checkValidationOfEmail(String email) {
        return emailRepository.existsByEmail(email);
    }

    // get all temporary users
    public List<TempUser> getALlTempUser() {
        return tempUserRepository.findAll();
    }

    public List<TempUser> getAllNotEvalualedUser() {
        return tempUserRepository.findAllByIsEvaluated(false);
    }

    public List<TempUser> getAllEvalualedUserAndRefused() {
        return tempUserRepository.findAllByIsEvaluated(true);
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
        /** set a role */
        Role role = roleRepository.getRoleByName("apprenant");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        System.out.println("the role size" + roleSet.size());
        for (User user : usersFrTempUser) {
            user.setRoles(roleSet);
        }
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

    @Transactional
    public void accepterUser(long id) {
        TempUser tempUser = tempUserRepository.getOne(id);
        if (tempUser != null) {
            Role role = roleRepository.getRoleByName("apprenant");
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            User user = tempUser.createUser();
            user.setRoles(roleSet);
            userService.addUser(user);
            tempUserRepository.deleteById(tempUser.getId());

            /** after acceptation of the user send an email to the user */
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(user.getEmail());
//            //message.setSubject("reservation accepted " + user.getFirstname());
//            message.setText("your account is approved");
//            mailSender.send(message);
        }
    }

    @Transactional
    public void refuserUser(long id) {
        TempUser tempUser = tempUserRepository.getOne(id);
        if (tempUser != null) {
            tempUser.setEvaluated(true);
            tempUserRepository.flush();
        }
    }

    public void deleteUserById(long id) {
        tempUserRepository.deleteById(id);
    }
}
