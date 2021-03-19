package com.spring.boot.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.CustomerDTO;
import com.spring.boot.mapper.CustomerMapper;
import com.spring.boot.model.Customer;

@Service
@Profile("map")
public class CustomerServiceMapImpl implements CustomerService {
	private Map<Long, Customer> customerMap = new HashMap<>();
	private CustomerMapper customerMapper;

	public CustomerServiceMapImpl(final CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		// TODO Auto-generated method stub
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		// TODO Auto-generated method stub
		if (customerMap != null && customerMap.size() > 0) {
			System.out.println("Customer Map:Get all Customers");
			customerDTOList = customerMap.values().stream().map(customerMapper::customerToCustomerDTO)
					.collect(Collectors.toList());
		}
		return customerDTOList;
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		// TODO Auto-generated method stub
		CustomerDTO customerDTO = null;
		if (id != null) {
			if (customerMap != null && customerMap.containsKey(id)) {
				Customer customer = customerMap.get(id);
				if (customer != null) {
					System.out.println("Customer Map:Get customer by Id:" + id);
					customerDTO = customerMapper.customerToCustomerDTO(customer);
				}
			}

		}
		return customerDTO;
	}

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		CustomerDTO savedcustomerDTO = null;
		if (customerDTO != null) {
			if (customerDTO.getId() == null) {
				customerDTO.setId(getNextId());
			}
			Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
			if (customer != null) {
				System.out.println("Customer Map:Save Customer");
				customerMap.put(customer.getId(), customer);
				savedcustomerDTO = customerMapper.customerToCustomerDTO(customer);
			}
		}
		return savedcustomerDTO;
	}

	private Long getNextId() {
		// TODO Auto-generated method stub
		Long nextId = null;
		if (customerMap != null) {
			if (customerMap.size() == 0) {
				nextId = 1L;
			} else {
				nextId = Collections.max(customerMap.keySet()) + 1;
			}

		}
		return nextId;
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		System.out.println("Customer Map:update Customer");
		return createCustomer(customerDTO);
	}

	@Override
	public void deleteCustomer(Long id) {
		// TODO Auto-generated method stub
		if (id != null) {
			if (customerMap != null && customerMap.containsKey(id)) {
				System.out.println("Customer Map:Delete customer by Id:" + id);
				customerMap.remove(id);
			}

		}

	}

}
