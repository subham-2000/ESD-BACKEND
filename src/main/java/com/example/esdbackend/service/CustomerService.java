package com.example.esdbackend.service;

import com.example.esdbackend.dto.CustomerRequest;
import com.example.esdbackend.dto.CustomerResponse;
import com.example.esdbackend.dto.LoginRequest;
import com.example.esdbackend.entity.Customer;
import com.example.esdbackend.exception.CustomerNotFoundException;
import com.example.esdbackend.helper.EncryptionService;
import com.example.esdbackend.helper.JWTHelper;
import com.example.esdbackend.mapper.CustomerMapper;
import com.example.esdbackend.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;
   // private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);

        customer.setPassword(encryptionService.encode(customer.getPassword()));
        customerRepo.save(customer);
        return "Customer Created Successfully";
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

    public String login( LoginRequest request) {
    Customer customer = getCustomer(request.email());
        if(!encryptionService.validates(request.password(), customer.getPassword())) {
        return "Wrong Password or Email";
    }

        return jwtHelper.generateToken(request.email());
}
}
