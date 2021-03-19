package com.spring.boot.service;

import java.util.List;

import com.spring.boot.dto.CustomerDTO;

public interface CustomerService {
	List<CustomerDTO> getAllCustomers();

	CustomerDTO getCustomerById(final Long id);

	CustomerDTO createCustomer(final CustomerDTO customerDTO);

	CustomerDTO updateCustomer(final CustomerDTO customerDTO);

	void deleteCustomer(final Long id);
}
