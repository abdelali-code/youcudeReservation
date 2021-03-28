package com.youcode.reservation.model;

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
    @NotBlank(message = "firstname should not be empty")
    private String firstname;
    @NotBlank(message = "lastname should not be blanck")
    private String lastname;
    @NotBlank(message = "email should not be empty")
    @Email(message = "email is not valid")
    private String email;
    @Size(min = 6, message = "password length should be 6 character or more")
    private String password;
    private int num_presence;
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

    public int getNum_presence() {
        return num_presence;
    }

    public void setNum_presence(int num_presence) {
        this.num_presence = num_presence;
    }
}
