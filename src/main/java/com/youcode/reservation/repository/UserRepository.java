package com.youcode.reservation.repository;

import com.youcode.reservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmail(String email);
    User getUserByFirstname(String firstname);
    User getUserByLastname(String lastname);
}
