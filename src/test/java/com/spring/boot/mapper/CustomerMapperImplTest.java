package com.spring.boot.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.spring.boot.dto.CustomerDTO;
import com.spring.boot.model.Customer;

class CustomerMapperImplTest {
	private static final Long ID = 1L;
	private static final String FNAME = "Chaithu";
	private static final String LNAME = "Modem";
	private static final String EMAIL = "modem@gmail.com";
	private static final String CITY = "Hyderabad";
	private CustomerMapper customerMapper;

	@BeforeEach
	void setUp() throws Exception {
		customerMapper = new CustomerMapperImpl();
	}

	@Test
	void testCustomerToCustomerDTO() {
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setFirstName(FNAME);
		customer.setLastName(LNAME);
		customer.setEmail(EMAIL);
		customer.setCity(CITY);
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
		assertNotNull(customerDTO);
		assertEquals(ID, customerDTO.getId());
		assertEquals(FNAME, customerDTO.getFirstName());
		assertEquals(LNAME, customerDTO.getLastName());
		assertEquals(EMAIL, customerDTO.getEmail());
		assertEquals(CITY, customerDTO.getCity());

	}

	@Test
	void testCustomerDTOToCustomer() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(ID);
		customerDTO.setFirstName(FNAME);
		customerDTO.setLastName(LNAME);
		customerDTO.setEmail(EMAIL);
		customerDTO.setCity(CITY);
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		assertNotNull(customer);
		assertEquals(ID, customer.getId());
		assertEquals(FNAME, customer.getFirstName());
		assertEquals(LNAME, customer.getLastName());
		assertEquals(EMAIL, customer.getEmail());
		assertEquals(CITY, customer.getCity());
	}

	@Test
	public void testEmptyCustomer() {
		Customer customer = new Customer();
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
		assertNotNull(customerDTO);
		assertNull(customerDTO.getId());
		assertNull(customerDTO.getFirstName());
		assertNull(customerDTO.getLastName());
		assertNull(customerDTO.getEmail());
		assertNull(customerDTO.getCity());

	}

	@Test
	public void testEmptyCustomerDTO() {
		CustomerDTO customerDTO = new CustomerDTO();
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		assertNotNull(customer);
		assertNull(customer.getId());
		assertNull(customer.getFirstName());
		assertNull(customer.getLastName());
		assertNull(customer.getEmail());
		assertNull(customer.getCity());

	}

	@Test
	public void testNullCustomer() {
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(null);
		assertNull(customerDTO);
	}

	@Test
	public void testNullCustomerDTO() {
		Customer customer = customerMapper.customerDTOToCustomer(null);
		assertNull(customer);
	}

}
