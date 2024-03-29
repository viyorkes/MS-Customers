package com.customer.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private int Id;

    @Size(min=2, message="name should have atleast 2 characters")
    private String name;


    @Past(message ="Birth Date should be in the past")
    private Date birthDate;
    private String departament;
    private BigDecimal salary;

    @OneToMany(mappedBy = "customer")
    private List<Post> posts;

}
