package com.spring.boot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.spring.boot.dto.CustomerDTO;
import com.spring.boot.mapper.CustomerMapperImpl;

class CustomerServiceMapImplTest {
	private CustomerService customerService;
	private CustomerDTO customerDTO;
	private static final Long ID = 1L;
	private static final String FNAME = "Chaithu";
	private static final String LNAME = "Modem";
	private static final String EMAIL = "modem@gmail.com";
	private static final String CITY = "Hyderabad";

	@BeforeEach
	void setUp() throws Exception {
		customerService = new CustomerServiceMapImpl(new CustomerMapperImpl());
		customerDTO = new CustomerDTO();
		customerDTO.setId(ID);
		customerDTO.setFirstName(FNAME);
		customerDTO.setLastName(LNAME);
		customerDTO.setEmail(EMAIL);
		customerDTO.setCity(CITY);
		customerService.createCustomer(customerDTO);

	}

	@Test
	void testGetAllCustomers() {
		List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
		assertEquals(1, customerDTOList.size());
		CustomerDTO customerDTO = customerDTOList.get(0);
		assertEquals(ID, customerDTO.getId());
		assertEquals(FNAME, customerDTO.getFirstName());
		assertEquals(LNAME, customerDTO.getLastName());
		assertEquals(EMAIL, customerDTO.getEmail());
		assertEquals(CITY, customerDTO.getCity());
	}

	@Test
	void testGetCustomerById() {
		CustomerDTO customerDTO = customerService.getCustomerById(ID);
		assertNotNull(customerDTO);
		assertEquals(ID, customerDTO.getId());
		assertEquals(FNAME, customerDTO.getFirstName());
		assertEquals(LNAME, customerDTO.getLastName());
		assertEquals(EMAIL, customerDTO.getEmail());
		assertEquals(CITY, customerDTO.getCity());

	}

	@Test
	void testCreateCustomer() {
		customerDTO.setId(2L);
		customerDTO.setFirstName("patil");
		customerDTO.setLastName("lakshmi");
		customerDTO.setEmail("patil@gmail.com");
		customerDTO.setCity("Bangalore");
		CustomerDTO savedCustomerDTO = customerService.createCustomer(customerDTO);
		assertNotNull(savedCustomerDTO);
		List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
		assertEquals(2, customerDTOList.size());
		assertEquals(2L, savedCustomerDTO.getId());
		assertEquals(customerDTO.getFirstName(), savedCustomerDTO.getFirstName());
		assertEquals(customerDTO.getLastName(), savedCustomerDTO.getLastName());
		assertEquals(customerDTO.getEmail(), savedCustomerDTO.getEmail());
		assertEquals(customerDTO.getCity(), savedCustomerDTO.getCity());

	}

	@Test
	void testCreateCustomerForNullValue() {
		CustomerDTO savedCustomerDTO = customerService.createCustomer(null);
		assertNull(savedCustomerDTO);

	}

	@Test
	void testCreateCustomerWithoutId() {
		customerDTO.setFirstName("patil");
		customerDTO.setLastName("lakshmi");
		customerDTO.setEmail("patil@gmail.com");
		customerDTO.setCity("Bangalore");

		CustomerDTO savedCustomerDTO = customerService.createCustomer(customerDTO);
		assertNotNull(savedCustomerDTO);
		assertEquals(1L, savedCustomerDTO.getId());
		assertEquals(customerDTO.getFirstName(), savedCustomerDTO.getFirstName());
		assertEquals(customerDTO.getLastName(), savedCustomerDTO.getLastName());
		assertEquals(customerDTO.getEmail(), savedCustomerDTO.getEmail());
		assertEquals(customerDTO.getCity(), savedCustomerDTO.getCity());

	}

	@Test
	void testUpdateCustomer() {
		customerDTO.setFirstName("Chaithanya");
		customerService.updateCustomer(customerDTO);
		List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
		assertEquals(1, customerDTOList.size());
		assertEquals(ID, customerDTO.getId());
		assertEquals("Chaithanya", customerDTO.getFirstName());
		assertEquals(LNAME, customerDTO.getLastName());
		assertEquals(EMAIL, customerDTO.getEmail());
		assertEquals(CITY, customerDTO.getCity());

	}

	@Test
	void testDeleteCustomer() {
		customerService.deleteCustomer(ID);
		List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
		assertEquals(0, customerDTOList.size());
	}

}
