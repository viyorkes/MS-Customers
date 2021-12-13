package com.customer.service;


import com.customer.entity.Customer;
import com.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    private static List<Customer> customers = new ArrayList<>();

    public List<Customer> findAllCustomers(){
      return customerRepository.findAll();
    }

    public List<String> findAllDepartaments() {


        List<Customer> customer = customerRepository.findAll();

        List<String> result =
                customer.stream()
                        .map(Customer::getDepartament)
                        .distinct()
                        .collect(Collectors.toList());

        return result;


    }


    public Optional<Customer> findCustomer(int id) {
         return customerRepository.findById(id);

    }

    public Customer saveCustomer(Customer customer) {

        return customerRepository.save(customer);

    }

    public void deleteById(int id) {
        customerRepository.deleteById(id);

    }
}
