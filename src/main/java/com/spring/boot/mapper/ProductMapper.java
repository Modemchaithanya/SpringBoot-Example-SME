package com.spring.boot.mapper;

import org.springframework.stereotype.Component;

import com.spring.boot.dto.ProductDTO;
import com.spring.boot.model.Product;
@Component
public interface ProductMapper {
	ProductDTO productToProductDTO(final Product product);
	Product productDTOToProduct(final ProductDTO productDTO);
	
}
