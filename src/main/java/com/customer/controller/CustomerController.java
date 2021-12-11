package com.customer.controller;


import com.customer.entity.Customer;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> retrieveAllCustomers(){
        return customerService.findAllCustomers();

    }


    @GetMapping("/customer/{id}")
    public Optional<Customer> retrieveCustomer(@PathVariable int id) {
      return customerService.findCustomer(id);

    }


    @PostMapping("/createCustomer")
    public ResponseEntity<Object> createUser( @RequestBody Customer customer) {
        Customer savedUser = customerService.saveCustomer(customer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }





}

