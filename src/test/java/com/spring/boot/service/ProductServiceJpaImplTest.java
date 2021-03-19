package com.spring.boot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.spring.boot.dto.ProductDTO;
import com.spring.boot.mapper.ProductMapper;
import com.spring.boot.mapper.ProductMapperImpl;
import com.spring.boot.model.Product;
import com.spring.boot.repository.ProductRepository;

class ProductServiceJpaImplTest {
	private static final String HP = "HP";
	private static final String LENOVO = "LENOVO";
	private ProductService productService;
	private ProductMapper productMapper;
	@Mock
	private ProductRepository productRepository;
	private Product product1;
	private Product product2;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		productMapper = new ProductMapperImpl();
		productService = new ProductServiceJpaImpl(productMapper, productRepository);

		loadProducts();
	}

	private void loadProducts() {
		// TODO Auto-generated method stub
		product1 = new Product();
		product1.setId(1L);
		product1.setName(HP);
		product1.setDescription(HP);
		product1.setPrice(BigDecimal.TEN);

		product2 = new Product();
		product2.setId(2L);
		product2.setName(LENOVO);
		product2.setDescription(LENOVO);
		product2.setPrice(new BigDecimal(20));

	}

	@Test
	void testGetAllProducts() {
		when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
		List<ProductDTO> products = productService.getAllProducts();
		assertEquals(2, products.size());

	}

	@Test
	void testGetProductById() {
		when(productRepository.findById(anyLong())).thenReturn(Optional.of(product1));
		when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
		when(productRepository.findById(2L)).thenReturn(Optional.of(product2));
		ProductDTO product1 = productService.getProductById(1L);
		ProductDTO product2 = productService.getProductById(2L);
		assertEquals(HP, product1.getName());
		assertEquals(LENOVO, product2.getName());

	}

	@Test
	void testCreateProduct() {
		ProductDTO productDTO = new ProductDTO(3L, "new Product", "new Product", new BigDecimal(100));
		Product product = productMapper.productDTOToProduct(productDTO);

		when(productRepository.save(any())).thenReturn(product);

		ProductDTO createdProductDTO = productService.createProduct(productDTO);
		assertNotNull(createdProductDTO);
		assertEquals(3L, createdProductDTO.getId());
	}

	@Test
	void testCreateProductWithoutId() {
		ProductDTO productDTO = new ProductDTO(null, "new Product", "new Product", new BigDecimal(100));
		Product product = productMapper.productDTOToProduct(productDTO);
		product.setId(3L);
		when(productRepository.save(any())).thenReturn(product);

		ProductDTO createdProductDTO = productService.createProduct(productDTO);
		assertNotNull(createdProductDTO);
		assertEquals(3L, createdProductDTO.getId());
	}

	@Test
	void testUpdateProduct() {
		product2.setDescription("New Laptop");

		when(productRepository.save(any())).thenReturn(product2);
		ProductDTO updatedProductDTO = productService.updateProduct(productMapper.productToProductDTO(product1));
		assertEquals("New Laptop", updatedProductDTO.getDescription());

	}

	@Test
	void testDeleteProduct() {
		productService.deleteProduct(1L);
		verify(productRepository, times(1)).deleteById(any());
	}

}
