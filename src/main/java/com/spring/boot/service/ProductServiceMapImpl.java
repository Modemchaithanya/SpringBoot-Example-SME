package com.spring.boot.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.ProductDTO;
import com.spring.boot.mapper.ProductMapper;
import com.spring.boot.model.Product;

@Service
@Profile("map")
public class ProductServiceMapImpl implements ProductService {
	private Map<Long, Product> productMap = new HashMap<>();
	private ProductMapper productMapper;

	public ProductServiceMapImpl(final ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		List<ProductDTO> productDTOList = new ArrayList<>();
		System.out.println("Product Map:Get all Products");
		// TODO Auto-generated method stub
		if (productMap != null && productMap.size() > 0) {
			productDTOList = productMap.values().stream().map(productMapper::productToProductDTO)
					.collect(Collectors.toList());
		}
		return productDTOList;
	}

	@Override
	public ProductDTO getProductById(Long id) {
		// TODO Auto-generated method stub
		System.out.println("Product Map:Get product by Id:" + id);
		ProductDTO productDTO = null;
		if (id != null) {
			if (productMap != null && productMap.containsKey(id)) {
				Product product = productMap.get(id);
				if (product != null) {
					productDTO = productMapper.productToProductDTO(product);
				}
			}

		}
		return productDTO;
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		System.out.println("Product Map:Save Product");
		ProductDTO savedproductDTO = null;
		if (productDTO != null) {
			if (productDTO.getId() == null) {
				productDTO.setId(getNextId());
			}
			Product product = productMapper.productDTOToProduct(productDTO);
			if (product != null) {
				productMap.put(product.getId(), product);
				savedproductDTO = productMapper.productToProductDTO(product);
			}
		}
		return savedproductDTO;
	}

	private Long getNextId() {
		// TODO Auto-generated method stub
		Long nextId = null;
		if (productMap != null) {
			if (productMap.size() == 0) {
				nextId = 1L;
			} else {
				nextId = Collections.max(productMap.keySet()) + 1;
			}

		}
		return nextId;
	}

	@Override
	public ProductDTO updateProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		System.out.println("Product Map:update product");
		return createProduct(productDTO);
	}

	@Override
	public void deleteProduct(final Long id) {
		// TODO Auto-generated method stub
		System.out.println("Product Map:Delete product by Id:" + id);
		if (id != null) {
			if (productMap != null && productMap.containsKey(id)) {
				productMap.remove(id);
			}

		}
	}

}
