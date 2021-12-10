package com.customer.controller;


import com.customer.entity.Customer;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> retrieveAllCustomers(){
        return customerService.findAllCustomers();

    }


}

