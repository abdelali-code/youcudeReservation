package com.youcode.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 3, max = 45, message = "you firstname should be between 3 and 45 character")
    private String firstname;
    @Size(min = 3, max = 45, message = "you lastname should be between 3 and 45 character")
    private String lastname;
    @Size(max = 45, message = "your email is too tall")
    @NotBlank(message = "email should not be empty")
    @Email(message = "email is not valid")
    @Column(name = "email", unique = true)
    private String email;
    @Size(min = 6, max = 255, message = "password length should be 6 character or more")
    @JsonIgnore
    private String password;
    @Column(name = "num_presence")
    private int numPresence;

    @Transient
    private String confirmPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    public User() {}

    public User(long id, String firstName, String lastname, String email, String password) {
        this.id = id;
        this.firstname = firstName;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getNumPresence() {
        return numPresence;
    }

    public void setNumPresence(int numPresence) {
        this.numPresence = numPresence;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /** compare password with the confirmPassword */
    public boolean isPasswordConfirmed() {
        return this.getPassword().equals(this.getConfirmPassword());
    }
}
