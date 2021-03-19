package com.spring.boot.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.dto.ProductDTO;
import com.spring.boot.dto.ProductListDTO;
import com.spring.boot.exception.ProductValidationException;
import com.spring.boot.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private static final String PIPE = "|";
	private ProductService productService;

	public ProductController(final ProductService productService) {
		this.productService = productService;
	}

	/*
	 * @RequestMapping("/index") public String greetings() { return
	 * "Welcome to Spring Boot"; }
	 */
	@RequestMapping({ "/", "" })
	public ProductListDTO getAllProducts() {
		return new ProductListDTO(productService.getAllProducts());
	}

	@GetMapping("/{id}")
	public ProductDTO getProductById(@PathVariable final String id) {
		return productService.getProductById(Long.valueOf(id));

	}

	@PostMapping({ "/", "" })
	public ProductDTO createProduct(@Valid final @RequestBody ProductDTO productDTO, final BindingResult result) {
		StringBuilder stringBuilder = new StringBuilder();
		if (result != null && result.hasErrors()) {
			result.getAllErrors().forEach(error -> {
				stringBuilder.append(PIPE);
				stringBuilder.append(error.getDefaultMessage());
			});
			String validationErrors = stringBuilder.toString();
			System.out.println("Product Validation Errors:" + validationErrors);
			throw new ProductValidationException(validationErrors);
		}

		return productService.createProduct(productDTO);

	}

	@PutMapping("/{id}")
	public ProductDTO updateProduct(@PathVariable final String id, final @RequestBody ProductDTO productDTO) {
		// ProductDTO product = productService.getProductById(Long.valueOf(id));
		ProductDTO updatedProduct = null;
		if (productDTO != null) {
			if (id != null) {
				productDTO.setId(Long.valueOf(id));
			}
			updatedProduct = productService.updateProduct(productDTO);

		}
		return updatedProduct;

	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable final String id) {
		if (id != null) {
			productService.deleteProduct(Long.valueOf(id));
		}

	}

}
