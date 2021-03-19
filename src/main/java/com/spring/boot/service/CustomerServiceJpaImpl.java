package com.spring.boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.CustomerDTO;
import com.spring.boot.exception.CustomerNotFoundException;
import com.spring.boot.mapper.CustomerMapper;
import com.spring.boot.model.Customer;
import com.spring.boot.repository.CustomerRepository;

@Service
@Profile("jpa")
public class CustomerServiceJpaImpl implements CustomerService {
	private CustomerMapper customerMapper;
	private CustomerRepository customerRepository;

	public CustomerServiceJpaImpl(final CustomerMapper customerMapper, final CustomerRepository customerRepository) {
		this.customerMapper = customerMapper;
		this.customerRepository = customerRepository;

	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		// TODO Auto-generated method stub
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		List<Customer> customerList = customerRepository.findAll();
		if (customerList != null && customerList.size() > 0) {
			System.out.println("Customer JPA:Get all Customers");
			customerDTOList = customerList.stream().map(customerMapper::customerToCustomerDTO)
					.collect(Collectors.toList());
		}
		return customerDTOList;
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		// TODO Auto-generated method stub

		CustomerDTO customerDTO = null;
		if (id != null) {
			// Optional<Product> optionalProduct = productRepository.findById(id);
			Customer dbCustomer = customerRepository.findById(id)
					.orElseThrow(() -> new CustomerNotFoundException("Invalid Customer Id:" + id));
			if (dbCustomer != null) {
				System.out.println("Customer JPA:Get Customer by Id:" + id);
				customerDTO = customerMapper.customerToCustomerDTO(dbCustomer);
			}
		}

		return customerDTO;
	}

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		CustomerDTO savedCustomerDTO = null;
		if (customerDTO != null) {
			Customer customerToSave = customerMapper.customerDTOToCustomer(customerDTO);
			if (customerToSave != null) {
				System.out.println("Customer JPA:create  Customer");
				Customer savedCustomer = customerRepository.save(customerToSave);
				savedCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
			}
		}
		return savedCustomerDTO;
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		System.out.println("Customer JPA:update customer");
		return createCustomer(customerDTO);
	}

	@Override
	public void deleteCustomer(Long id) {
		// TODO Auto-generated method stub
		if (id != null) {
			System.out.println("Customer JPA:Delete customer by Id:" + id);
			customerRepository.deleteById(id);
		}

	}

}
