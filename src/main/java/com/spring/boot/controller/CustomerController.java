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
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.dto.CustomerDTO;
import com.spring.boot.dto.CustomerListDTO;
import com.spring.boot.exception.CustomerValidationException;
import com.spring.boot.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	private static final Object PIPE = "|";
	private CustomerService customerService;

	public CustomerController(final CustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping({ "/", "" })
	public CustomerListDTO getAllCustomers() {
		return new CustomerListDTO(customerService.getAllCustomers());
	}

	@GetMapping("/{id}")
	public CustomerDTO getCustomerById(@PathVariable final String id) {
		return customerService.getCustomerById(Long.valueOf(id));

	}

	@PostMapping({ "/", "" })
	public CustomerDTO createCustomer(@Valid final @RequestBody CustomerDTO customerDTO, BindingResult result) {
		StringBuilder stringBuilder = new StringBuilder();
		if (result != null && result.hasErrors()) {
			result.getAllErrors().forEach(error -> {
				stringBuilder.append(PIPE);
				stringBuilder.append(error.getDefaultMessage());
			});
			String validationErrors = stringBuilder.toString();
			System.out.println("Customer Validation Errors:" + validationErrors);
			throw new CustomerValidationException(validationErrors);
		}
		return customerService.createCustomer(customerDTO);

	}

	@PutMapping("/{id}")
	public CustomerDTO updateCustomer(@PathVariable final String id, final @RequestBody CustomerDTO customerDTO) {
		// ProductDTO product = productService.getProductById(Long.valueOf(id));
		CustomerDTO updatedCustomer = null;
		if (customerDTO != null) {
			if (id != null) {
				customerDTO.setId(Long.valueOf(id));
			}
			updatedCustomer = customerService.updateCustomer(customerDTO);

		}
		return updatedCustomer;

	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable final String id) {
		if (id != null) {
			customerService.deleteCustomer(Long.valueOf(id));
		}

	}

}
