package com.customer.service;


import com.customer.entity.Customer;
import com.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAllCustomers(){
      return customerRepository.findAll();
    }


}
