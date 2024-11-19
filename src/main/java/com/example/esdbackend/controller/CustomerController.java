package com.example.esdbackend.controller;


import com.example.esdbackend.dto.CustomerRequest;
import com.example.esdbackend.dto.CustomerResponse;
import com.example.esdbackend.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor

public class CustomerController {


private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){
              return   ResponseEntity.ok(customerService.createCustomer(request));
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }
}
