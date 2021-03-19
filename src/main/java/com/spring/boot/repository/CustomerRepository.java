package com.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
