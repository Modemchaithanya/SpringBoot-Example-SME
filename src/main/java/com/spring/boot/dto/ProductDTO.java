package com.spring.boot.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ProductDTO {
	private Long id;
	@NotBlank(message = "{custom.message}")
	@Size(min = 2, max = 20, message = "Product Name should be between 2-20 characters")
	@Pattern(regexp = "[a-zA-Z]*")
	private String name;
	@NotBlank(message = "Product Description cannot be Empty")
	@Size(min = 2, max = 50, message = "Product Description should be between 2-50 characters")
	private String description;
	private BigDecimal price;
	private CustomerDTO customer;

	/*
	 * public ProductDTO() {
	 * 
	 * }
	 */
	public ProductDTO(final Long id, final String name, final String description, final BigDecimal price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	/*
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 * 
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 * 
	 * public String getDescription() { return description; }
	 * 
	 * public void setDescription(String description) { this.description =
	 * description; }
	 * 
	 * public BigDecimal getPrice() { return price; }
	 * 
	 * public void setPrice(BigDecimal price) { this.price = price; }
	 */

	@Override
	public String toString() {
		return String.format("ProductDTO [name=%s, description=%s, price=%s]", name, description, price);
	}
}
