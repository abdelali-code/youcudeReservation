package com.youcode.reservation.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "temp_users", uniqueConstraints= @UniqueConstraint(columnNames={"email"}))
public class TempUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "firstname should not be empty")
    @Size(min = 3, max = 45, message = "you firstname should be between 3 and 45 character")
    private String firstname;
    @NotBlank(message = "lastname should not be blanck")
    @Size(min = 3, max = 45, message = "you lastname should be between 3 and 45 character")
    private String lastname;

    @Size(max = 45, message = "your email is too tall")
    @NotBlank(message = "email should not be empty")
    @Email(message = "email is not valid")
    @Column(name = "email", unique = true)
    private String email;

    @Size(min = 6, max = 255, message = "password length should be 6 character or more")
    private String password;

    @Transient
    private String confirmPassword;

    private boolean is_valid = false;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isIs_valid() {
        return is_valid;
    }

    public void setIs_valid(boolean is_valid) {
        this.is_valid = is_valid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    /** create User from Temp User */
    public User createUser(){
        User user = new User(id, firstname, lastname, email, password);
        return user;
    }

    /** compare password with the confirmPassword */
    public boolean isPasswordConfirmed() {
        return password.equals(confirmPassword);
    }
}
