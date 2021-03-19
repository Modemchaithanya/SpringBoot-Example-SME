package com.spring.boot.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.dto.CustomerDTO;
import com.spring.boot.service.CustomerService;

public class CustomerControllerTest {
	private CustomerController customerController;
	@Mock
	private CustomerService customerService;
	private MockMvc mockMvc;
	private CustomerDTO cust1;
	private CustomerDTO cust2;
	private static final String BASE_URL = "/api/customers";

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		customerController = new CustomerController(customerService);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
		loadCustomers();
	}

	private void loadCustomers() {
		// TODO Auto-generated method stub
		cust1 = new CustomerDTO();
		cust1.setId(1L);
		cust1.setFirstName("Steven");
		cust1.setLastName("King");
		cust1.setEmail("king@gmail.com");
		cust1.setCity("Hyderabad");

		cust2 = new CustomerDTO();
		cust2.setId(2L);
		cust2.setFirstName("Neena");
		cust2.setLastName("Kochhar");
		cust2.setEmail("kochhar@gmail.com");
		cust2.setCity("Pune");

	}

	@Test
	void testGetAllCustomers() throws Exception {
		when(customerService.getAllCustomers()).thenReturn(Arrays.asList(cust1, cust2));
		mockMvc.perform(
				get(BASE_URL).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.customers", Matchers.hasSize(2)));
	}

	@Test
	void testGetCustomerById() throws Exception {
		when(customerService.getCustomerById(ArgumentMatchers.anyLong())).thenReturn(cust1);
		mockMvc.perform(get(BASE_URL + "/1").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", Matchers.equalTo("Steven")));
	}

	@Test
	void testCreateCustomer() throws Exception {
		CustomerDTO given = new CustomerDTO();
		given.setFirstName("praveen");
		given.setLastName("modem");
		given.setEmail("sai@gmail.com");
		given.setCity("Mysore");

		CustomerDTO created = new CustomerDTO();
		created.setId(3L);
		created.setFirstName("praveen");
		created.setLastName("modem");
		created.setEmail("sai@gmail.com");
		created.setCity("Mysore");

		when(customerService.createCustomer(ArgumentMatchers.any())).thenReturn(created);
		mockMvc.perform(post(BASE_URL).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString(given))).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", Matchers.equalTo("praveen")));
	}

	private String jsonString(Object object) {
		String jsonString = null;
		if (object != null) {
			try {
				jsonString = new ObjectMapper().writeValueAsString(object);
			} catch (JsonProcessingException e) {
				e.printStackTrace();

			}
		}
		// TODO Auto-generated method stub
		return jsonString;
	}

	@Test
	void testUpdateCustomer() throws Exception {
		cust2.setFirstName("Reena");
		when(customerService.updateCustomer(ArgumentMatchers.any())).thenReturn(cust2);
		mockMvc.perform(put(BASE_URL + "/2").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString(cust2))).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", Matchers.equalTo("Reena")));
	}

	@Test
	void testDeleteCustomer() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/2"));
		verify(customerService, times(1)).deleteCustomer(2L);
	}

}
