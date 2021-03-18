package com.youcode.reservation.repository;

import com.youcode.reservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User getUserByEmail(String email);
    public User getUserByFirstName(String firstname);
    public User getUserByLastname(String lastname);
}
