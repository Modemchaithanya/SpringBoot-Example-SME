package com.spring.boot.dto;

import java.util.List;

public class CustomerListDTO {
	private List<CustomerDTO> customers;

	public CustomerListDTO() {

	}

	public CustomerListDTO(final List<CustomerDTO> customers) {
		this.customers = customers;

	}

	public List<CustomerDTO> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerDTO> customers) {
		this.customers = customers;
	}

}
