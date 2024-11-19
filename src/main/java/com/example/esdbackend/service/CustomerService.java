package com.example.esdbackend.service;

import com.example.esdbackend.dto.CustomerRequest;
import com.example.esdbackend.dto.CustomerResponse;
import com.example.esdbackend.entity.Customer;
import com.example.esdbackend.exception.CustomerNotFoundException;
import com.example.esdbackend.mapper.CustomerMapper;
import com.example.esdbackend.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

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

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);
        return customerMapper.toCustomerResponse(customer);
    }

    private Customer getCustomer(String email) {
        return customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }
}
