package com.library.programmingexercise.repository;

import com.library.programmingexercise.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// an interface for the AdminRepository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String email); // find admin by email
}



