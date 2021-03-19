package com.spring.boot.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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
import com.spring.boot.dto.ProductDTO;
import com.spring.boot.service.ProductService;

public class ProductControllerTest {
	private static final String LAPTOP = "Laptop";
	private static final String TABLET = "Tablet";
	private static final String BASE_URL = "/api/products";
	private static final String MOBILE = "Mobile";
	private ProductController productController;
	@Mock
	private ProductService productService;
	private MockMvc mockMvc;
	private ProductDTO prod1;
	private ProductDTO prod2;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		productController = new ProductController(productService);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
		loadProducts();

	}

	private void loadProducts() {
		// TODO Auto-generated method stub
		prod1 = new ProductDTO();
		prod1.setId(1L);
		prod1.setName(LAPTOP);
		prod1.setDescription(LAPTOP);
		prod1.setPrice(new BigDecimal(1000));

		prod2 = new ProductDTO();
		prod2.setId(2L);
		prod2.setName(TABLET);
		prod2.setDescription(TABLET);
		prod2.setPrice(new BigDecimal(500));

	}

	@Test
	void testGetAllProducts() throws Exception {
		when(productService.getAllProducts()).thenReturn(Arrays.asList(prod1, prod2));
		mockMvc.perform(
				get(BASE_URL).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.products", Matchers.hasSize(2)));
	}

	@Test
	void testGetProductById() throws Exception {
		when(productService.getProductById(ArgumentMatchers.anyLong())).thenReturn(prod1);
		mockMvc.perform(get(BASE_URL + "/1").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", Matchers.equalTo(LAPTOP)));

	}

	@Test
	void testCreateProduct() throws Exception {
		ProductDTO given = new ProductDTO();
		// given.setId(3L);
		given.setName(MOBILE);
		given.setDescription(MOBILE);
		given.setPrice(new BigDecimal(600));

		ProductDTO created = new ProductDTO();
		created.setId(3L);
		created.setName(MOBILE);
		created.setDescription(MOBILE);
		created.setPrice(new BigDecimal(600));
		when(productService.createProduct(ArgumentMatchers.any())).thenReturn(created);
		mockMvc.perform(post(BASE_URL).accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString(given))).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", Matchers.equalTo(MOBILE)));
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
	void testUpdateProduct() throws Exception {
		prod2.setName(MOBILE);
		when(productService.updateProduct(ArgumentMatchers.any())).thenReturn(prod2);
		mockMvc.perform(put(BASE_URL + "/2").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString(prod2))).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", Matchers.equalTo(MOBILE)));

	}

	@Test
	void testDeleteProduct() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/2"));
		verify(productService, times(1)).deleteProduct(2L);
	}

}
