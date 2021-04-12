package com.youcode.reservation.repository;

import com.youcode.reservation.model.Role;
import com.youcode.reservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmail(String email);
    User getUserByFirstname(String firstname);
    User getUserByLastname(String lastname);


    List<User> findAllByRoles_Name(String name);
}
