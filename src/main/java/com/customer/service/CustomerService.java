package com.customer.service;


import com.customer.entity.Customer;
import com.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAllCustomers(){
      return customerRepository.findAll();
    }


    public Optional<Customer> findCustomer(int id) {
         return customerRepository.findById(id);


    }

    public Customer saveCustomer(Customer customer) {

        return customerRepository.save(customer);


    }
}
