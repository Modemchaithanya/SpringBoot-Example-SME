package com.spring.boot.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
	private Long id;
	@NotBlank(message = "{customer.fname.message}")
	@Size(min = 2, max = 20, message = "First Name should be between 2-20 characters")
	@Pattern(regexp = "[a-zA-Z]*")
	private String firstName;
	@NotBlank(message = "{customer.lname.message}")
	@Size(min = 2, max = 20, message = "Last Name should be between 2-20 characters")
	@Pattern(regexp = "[a-zA-Z]*")
	private String lastName;
	@NotBlank(message = "Email cannot be Empty!!")
	private String email;
	@NotBlank(message = "City name cannot be Empty!!")
	private String city;
	// private List<ProductDTO> products;

	public CustomerDTO(final Long id, final String firstName, final String lastName, final String email,
			final String city) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
	}

	/*
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 * 
	 * public String getFirstName() { return firstName; }
	 * 
	 * public void setFirstName(String firstName) { this.firstName = firstName; }
	 * 
	 * public String getLastName() { return lastName; }
	 * 
	 * public void setLastName(String lastName) { this.lastName = lastName; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 * 
	 * public String getCity() { return city; }
	 * 
	 * public void setCity(String city) { this.city = city; }
	 */

	@Override
	public String toString() {
		return String.format("CustomerDTO [id=%s, firstName=%s, lastName=%s, email=%s, city=%s]", id, firstName,
				lastName, email, city);
	}

}
