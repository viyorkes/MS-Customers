package com.customer.controller;


import com.customer.entity.Customer;
import com.customer.entity.Post;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CustomerController {


    @Autowired
    private CustomerService customerService;



    @GetMapping("/customers")
    public List<Customer> retrieveAllCustomers(){
        return customerService.findAllCustomers();

    }


    @GetMapping("/customer/departaments")
    public List<String> retrieveAllDepartaments(){
        return customerService.findAllDepartaments();

    }

    @GetMapping("/customer/payrollSum")
    public BigDecimal retrieveAllPayrollSum(){
        return customerService.getPayrollSum();

    }

    @GetMapping("/customer/maxSalary")
    public Customer retrieveAMaxSalary(){
        return customerService.getMaxSalary();

    }

    @GetMapping("/customer/{id}/posts")
    public  List<Post>  retrievePostsFromCustomer(@PathVariable int id) {
        return customerService.findCustomerPosts(id);

    }


    @GetMapping("/customer/{id}")
    public EntityModel<Customer>retrieveCustomer(@PathVariable int id) {

      return customerService.findCustomer(id);

    }


    @PostMapping("/createCustomer")
    public ResponseEntity<Object> createUser( @RequestBody Customer customer) {
        Customer savedUser = customerService.saveCustomer(customer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }



    @PostMapping("/customer/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {

    return customerService.createPost(id,post);

    }


    @DeleteMapping("/deleteCustomer/{id}")
    public void deleteUser(@PathVariable int id) {
        customerService.deleteById(id);
    }

}

