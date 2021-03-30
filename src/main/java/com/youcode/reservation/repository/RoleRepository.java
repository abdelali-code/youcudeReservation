package com.youcode.reservation.repository;

import com.youcode.reservation.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Repository> {
    Set<Role> getRoleByName(String name);
}
