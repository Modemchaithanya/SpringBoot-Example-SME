package com.spring.boot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.spring.boot.dto.ProductDTO;
import com.spring.boot.mapper.ProductMapperImpl;

class ProductServiceMapImplTest {
	private ProductService productService;
	private ProductDTO productDTO;
	private static final Long ID = 1L;
	private static final String NAME = "LAPTOP";
	private static final String DESCRIPTION = "LAPTOP";
	private static final BigDecimal PRICE = new BigDecimal(100);

	@BeforeEach
	void setUp() throws Exception {
		productService = new ProductServiceMapImpl(new ProductMapperImpl());
		productDTO = new ProductDTO();
		productDTO.setId(ID);
		productDTO.setName(NAME);
		productDTO.setDescription(DESCRIPTION);
		productDTO.setPrice(PRICE);
		productService.createProduct(productDTO);
	}

	@Test
	void testGetAllProducts() {
		List<ProductDTO> productDTOList = productService.getAllProducts();
		assertEquals(1, productDTOList.size());
		ProductDTO productDTO = productDTOList.get(0);
		assertEquals(ID, productDTO.getId());
		assertEquals(NAME, productDTO.getName());
		assertEquals(DESCRIPTION, productDTO.getDescription());
		assertEquals(PRICE, productDTO.getPrice());
	}

	@Test
	void testGetProductById() {
		ProductDTO productDTO = productService.getProductById(ID);
		assertNotNull(productDTO);
		assertEquals(ID, productDTO.getId());
		assertEquals(NAME, productDTO.getName());
		assertEquals(DESCRIPTION, productDTO.getDescription());
		assertEquals(PRICE, productDTO.getPrice());
	}

	@Test
	void testGetProductByNonExistenceId() {
		ProductDTO productDTO = productService.getProductById(5L);
		assertNull(productDTO);
	}

	@Test
	void testCreateProduct() {
		productDTO.setId(2L);
		productDTO.setName("HP");
		productDTO.setDescription("LAPTOP");
		productDTO.setPrice(new BigDecimal(200));
		ProductDTO savedProductDTO = productService.createProduct(productDTO);
		assertNotNull(savedProductDTO);
		List<ProductDTO> productDTOList = productService.getAllProducts();
		assertEquals(2, productDTOList.size());
		assertEquals(2L, savedProductDTO.getId());
		assertEquals(productDTO.getName(), savedProductDTO.getName());
		assertEquals(productDTO.getDescription(), savedProductDTO.getDescription());
		assertEquals(productDTO.getPrice(), savedProductDTO.getPrice());

	}

	@Test
	void testCreateProductForNullValue() {
		ProductDTO savedProductDTO = productService.createProduct(null);
		assertNull(savedProductDTO);

	}

	@Test
	void testCreateProductWithoutId() {
		productDTO.setName("HP");
		productDTO.setDescription("LAPTOP");
		productDTO.setPrice(new BigDecimal(200));
		ProductDTO savedProductDTO = productService.createProduct(productDTO);
		assertNotNull(savedProductDTO);
		assertEquals(1L, savedProductDTO.getId());
		assertEquals(productDTO.getName(), savedProductDTO.getName());
		assertEquals(productDTO.getDescription(), savedProductDTO.getDescription());
		assertEquals(productDTO.getPrice(), savedProductDTO.getPrice());

	}

	@Test
	void testUpdateProduct() {
		// productDTO.setId(1L);
		productDTO.setName("HP");
		// productDTO.setDescription("LAPTOP");
		// productDTO.setPrice(new BigDecimal(200));

		productService.updateProduct(productDTO);
		List<ProductDTO> productDTOList = productService.getAllProducts();
		assertEquals(1, productDTOList.size());
		assertEquals("HP", productDTO.getName());
		// assertEquals(NAME,productDTO.getName());
		assertEquals(ID, productDTO.getId());
		assertEquals(DESCRIPTION, productDTO.getDescription());

	}

	@Test
	void testDeleteProduct() {
		productService.deleteProduct(ID);
		List<ProductDTO> productDTOList = productService.getAllProducts();
		assertEquals(0, productDTOList.size());
	}

}
