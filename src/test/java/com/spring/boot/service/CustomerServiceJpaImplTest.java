package com.spring.boot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.spring.boot.dto.CustomerDTO;
import com.spring.boot.mapper.CustomerMapper;
import com.spring.boot.mapper.CustomerMapperImpl;
import com.spring.boot.model.Customer;
import com.spring.boot.repository.CustomerRepository;

class CustomerServiceJpaImplTest {
	private CustomerService customerService;
	private CustomerMapper customerMapper;
	@Mock
	private CustomerRepository customerRepository;
	private Customer cust1;
	private Customer cust2;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		customerMapper = new CustomerMapperImpl();
		customerService = new CustomerServiceJpaImpl(customerMapper, customerRepository);

		loadProducts();
	}

	private void loadProducts() {
		// TODO Auto-generated method stub
		cust1 = new Customer();
		cust1.setId(1L);
		cust1.setFirstName("chaithu");
		cust1.setLastName("modem");
		cust1.setEmail("chaithu@gmail.com");
		cust1.setCity("Bangalore");

		cust2 = new Customer();
		cust2.setId(2L);
		cust2.setFirstName("shaik");
		cust2.setLastName("reena");
		cust2.setEmail("reena@gmail.com");
		cust2.setCity("Hyderabad");

	}

	@Test
	void testGetAllCustomers() {
		when(customerRepository.findAll()).thenReturn(Arrays.asList(cust1, cust2));
		List<CustomerDTO> customers = customerService.getAllCustomers();
		assertEquals(2, customers.size());

	}

	@Test
	void testGetCustomerById() {
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(cust1));
		when(customerRepository.findById(1L)).thenReturn(Optional.of(cust1));
		when(customerRepository.findById(2L)).thenReturn(Optional.of(cust2));
		CustomerDTO customer1 = customerService.getCustomerById(1L);
		CustomerDTO customer2 = customerService.getCustomerById(2L);
		assertEquals("chaithu", customer1.getFirstName());
		assertEquals("shaik", customer2.getFirstName());
	}

	@Test
	void testCreateCustomer() {
		CustomerDTO customerDTO = new CustomerDTO(3L, "patil", "lakshmi", "patil@gmail.com", "Guntakal");
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		when(customerRepository.save(any())).thenReturn(customer);

		CustomerDTO createdCustomerDTO = customerService.createCustomer(customerDTO);
		assertNotNull(createdCustomerDTO);
		assertEquals(3L, createdCustomerDTO.getId());
	}

	@Test
	void testCreateCustomerWithoutId() {
		CustomerDTO customerDTO = new CustomerDTO(null, "patil", "lakshmi", "patil@gmail.com", "Guntakal");
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		customer.setId(3L);
		when(customerRepository.save(any())).thenReturn(customer);

		CustomerDTO createdCustomerDTO = customerService.createCustomer(customerDTO);
		assertNotNull(createdCustomerDTO);
		assertEquals(3L, createdCustomerDTO.getId());
	}

	@Test
	void testUpdateCustomer() {
		cust2.setFirstName("Shikiligiri");

		when(customerRepository.save(any())).thenReturn(cust2);
		CustomerDTO updatedCustomerDTO = customerService.updateCustomer(customerMapper.customerToCustomerDTO(cust2));
		assertEquals("Shikiligiri", updatedCustomerDTO.getFirstName());
	}

	@Test
	void testDeleteCustomer() {
		customerService.deleteCustomer(1L);
		verify(customerRepository, times(1)).deleteById(any());
	}

}
