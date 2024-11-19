package com.example.esdbackend.repo;

import com.example.esdbackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
     Optional<Customer> findByEmail(String email);
}
