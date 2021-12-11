package com.customer.controller;

import com.customer.entity.Customer;
import com.customer.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerControllerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void retrieveAllCustomers() {

        Customer customer = new Customer();
        customer.setId(1);
        customer.setBirthDate(new Date());
        customer.setName("teste");
        customer.setSalary(new BigDecimal( 150));

        Iterable<Customer> customers = customerRepository.findAll();
        assertThat(customer).isNotNull();

    }
    }

