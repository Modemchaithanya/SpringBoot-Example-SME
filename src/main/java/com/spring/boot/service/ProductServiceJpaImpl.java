package com.spring.boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.ProductDTO;
import com.spring.boot.exception.ProductNotFoundException;
import com.spring.boot.mapper.ProductMapper;
import com.spring.boot.model.Product;
import com.spring.boot.repository.ProductRepository;

@Service
@Profile("jpa")
//@Primary
public class ProductServiceJpaImpl implements ProductService {
	private ProductMapper productMapper;
	private ProductRepository productRepository;

	public ProductServiceJpaImpl(final ProductMapper productMapper, final ProductRepository productRepository) {
		this.productMapper = productMapper;
		this.productRepository = productRepository;

	}

	@Override
	public List<ProductDTO> getAllProducts() {
		// TODO Auto-generated method stub
		System.out.println("Product JPA:Get all Products");
		List<ProductDTO> productDTOList = new ArrayList<>();
		List<Product> productList = productRepository.findAll();
		if (productList != null && productList.size() > 0) {
			productDTOList = productList.stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
		}
		return productDTOList;
	}

	@Override
	public ProductDTO getProductById(Long id) {
		// TODO Auto-generated method stub
		System.out.println("Product JPA:Get product by Id:" + id);
		ProductDTO productDTO = null;
		if (id != null) {
			// Optional<Product> optionalProduct = productRepository.findById(id);
			Product dbProduct = productRepository.findById(id)
					.orElseThrow(() -> new ProductNotFoundException("Invalid Product Id:" + id));
			if (dbProduct != null) {
				productDTO = productMapper.productToProductDTO(dbProduct);
			}
		}

		return productDTO;
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		System.out.println("Product JPA:Save Product");
		ProductDTO savedProductDTO = null;
		if (productDTO != null) {
			Product productToSave = productMapper.productDTOToProduct(productDTO);
			if (productToSave != null) {
				Product savedProduct = productRepository.save(productToSave);
				savedProductDTO = productMapper.productToProductDTO(savedProduct);
			}
		}
		return savedProductDTO;
	}

	@Override
	public ProductDTO updateProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		System.out.println("Product JPA:update product");
		return createProduct(productDTO);
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		System.out.println("Product JPA:Delete product by Id:" + id);
		if (id != null) {
			productRepository.deleteById(id);
		}

	}

}
