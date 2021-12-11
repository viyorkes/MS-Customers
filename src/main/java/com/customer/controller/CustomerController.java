package com.customer.controller;


import com.customer.entity.Customer;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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


}

