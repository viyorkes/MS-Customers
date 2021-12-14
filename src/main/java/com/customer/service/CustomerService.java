package com.customer.service;


import com.customer.entity.Customer;
import com.customer.entity.Post;
import com.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
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


    public BigDecimal getPayrollSum() {

        List<Customer> customer = customerRepository.findAll();

        BigDecimal sum = customer.stream()
                .map(x -> x.getSalary())    // map
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sum;

    }


    public Customer getMaxSalary() {

        List<Customer> customer = customerRepository.findAll();

        Customer maxSalary = customer
                .stream()
                .max(Comparator.comparing(Customer::getSalary))
                .orElseThrow(NoSuchElementException::new);

        return maxSalary;

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

    public List<Post> findCustomerPosts(int id) {

        Optional<Customer> customerOP = customerRepository.findById(id);


        return customerOP.get().getPosts();

    }
}
