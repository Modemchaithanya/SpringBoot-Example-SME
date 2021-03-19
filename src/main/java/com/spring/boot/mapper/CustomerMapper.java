package com.spring.boot.mapper;

import com.spring.boot.dto.CustomerDTO;
import com.spring.boot.model.Customer;

public interface CustomerMapper {
	CustomerDTO customerToCustomerDTO(final Customer customer);

	Customer customerDTOToCustomer(final CustomerDTO customerDTO);
}
