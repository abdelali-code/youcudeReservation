package com.youcode.reservation.model;

import javax.persistence.*;

@Entity
@Table(name = "temp_users")
public class TempUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    private String password;
    @Transient
    private String confirmPassword;
    private boolean is_valid = false;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
        User user = new User(id, firstName, lastname, email, password);
        return user;
    }
}
