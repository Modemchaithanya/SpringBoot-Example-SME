package com.spring.boot.dto;

import java.util.List;

public class ProductListDTO {
	private List<ProductDTO> products;
	public ProductListDTO() {
		
	}
	

	public ProductListDTO(final List<ProductDTO> products) {
		this.products = products;
	}


	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
	
}
