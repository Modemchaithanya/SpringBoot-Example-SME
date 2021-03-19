package com.spring.boot.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.spring.boot.dto.ProductDTO;
import com.spring.boot.model.Product;

class ProductMapperImplTest {
	private ProductMapper productMapper;
	private static final Long ID=1L;
	private static final String  NAME="LAPTOP";
	private static final String DESCRIPTION="LAPTOP";
	private static final BigDecimal PRICE=new BigDecimal(100);
	

	@BeforeEach
	void setUp() throws Exception {
		productMapper=new ProductMapperImpl();
	}

	@Test
	void testProductToProductDTO() {
		Product product=new Product();
		product.setId(ID);
		product.setName(NAME);
		product.setDescription(DESCRIPTION);
		product.setPrice(PRICE);
		ProductDTO productDTO=productMapper.productToProductDTO(product);
		assertNotNull(productDTO);
		assertEquals(ID,productDTO.getId());
		assertEquals(NAME,productDTO.getName());
		assertEquals(DESCRIPTION,productDTO.getDescription());
		assertEquals(PRICE,productDTO.getPrice());
		
	}

	@Test
	void testProductDTOToProduct() {
		ProductDTO productDTO=new ProductDTO();
		productDTO.setId(ID);
		productDTO.setName(NAME);
		productDTO.setDescription(DESCRIPTION);
		productDTO.setPrice(PRICE);
		Product product=productMapper.productDTOToProduct(productDTO);
		assertNotNull(product);
		assertEquals(ID,product.getId());
		assertEquals(NAME,product.getName());
		assertEquals(DESCRIPTION,product.getDescription());
		assertEquals(PRICE,product.getPrice());
	}
	@Test
	public void testEmptyProduct() {
		Product product=new Product();
		ProductDTO productDTO=productMapper.productToProductDTO(product);
		assertNotNull(productDTO);
		assertNull(productDTO.getId());
		assertNull(productDTO.getName());
		assertNull(productDTO.getDescription());
		assertNull(productDTO.getPrice());
		
		
	}
	@Test
	public void testEmptyProductDTO() {
		ProductDTO productDTO=new ProductDTO();
		Product product=productMapper.productDTOToProduct(productDTO);
		assertNotNull(product);
		assertNull(product.getId());
		assertNull(product.getName());
		assertNull(product.getDescription());
		assertNull(product.getPrice());
		
		
	}
	@Test
	public void testNullProduct() {
		ProductDTO productDTO=productMapper.productToProductDTO(null);
		assertNull(productDTO);
	}
	@Test
	public void testNullProductDTO() {
		Product product=productMapper.productDTOToProduct(null);
		assertNull(product);
	}

	
}
