package com.example.esdbackend.service;

import com.example.esdbackend.dto.CustomerRequest;
import com.example.esdbackend.entity.Customer;
import com.example.esdbackend.mapper.CustomerMapper;
import com.example.esdbackend.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;


    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customerRepo.save(customer);
        return "Customer created Successfully";
    }
}
