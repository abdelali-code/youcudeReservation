package com.youcode.reservation.rest;

import com.youcode.reservation.model.Role;
import com.youcode.reservation.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleRestController {

    @Autowired
    private RoleRepository roleRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/role")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
