package com.customer.service;


import com.customer.entity.Customer;
import com.customer.entity.Post;
import com.customer.repository.CustomerRepository;
import com.customer.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PostRepository postRepository;





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


    public EntityModel<Customer> findCustomer(int id) {



        Optional<Customer> customer = customerRepository.findById(id);

        EntityModel<Customer> resource = EntityModel.of(customer.get());

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAllCustomers());

        resource.add(linkTo.withRel("all-users"));

        return resource;

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

    public ResponseEntity<Object> createPost(int id,Post post) {


        Optional<Customer> customerOP = customerRepository.findById(id);

        Customer customer = customerOP.get();
        post.setCustomer(customer);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
}
