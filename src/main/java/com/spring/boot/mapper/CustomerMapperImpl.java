package com.spring.boot.mapper;

import org.springframework.stereotype.Component;

import com.spring.boot.dto.CustomerDTO;
import com.spring.boot.model.Customer;

@Component
public class CustomerMapperImpl implements CustomerMapper {
	// private ProductMapper productMapper;

	/*
	 * public ProductMapper getProductMapper() { return productMapper; }
	 * 
	 * @Autowired public void setProductMapper(ProductMapper productMapper) {
	 * this.productMapper = productMapper; }
	 */

	public CustomerMapperImpl() {

	}

	@Override
	public CustomerDTO customerToCustomerDTO(Customer customer) {
		// TODO Auto-generated method stub
		if (customer == null) {
			return null;
		}
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setFirstName(customer.getFirstName());
		customerDTO.setLastName(customer.getLastName());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setCity(customer.getCity());
		/*
		 * if (customer.getProducts() != null && customer.getProducts().size() > 0) {
		 * customerDTO.setProducts(customer.getProducts().stream().map(productMapper::
		 * productToProductDTO) .collect(Collectors.toList())); }
		 */

		return customerDTO;

	}

	@Override
	public Customer customerDTOToCustomer(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		if (customerDTO == null) {
			return null;
		}
		Customer customer = new Customer();
		customer.setId(customerDTO.getId());
		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(customerDTO.getLastName());
		customer.setEmail(customerDTO.getEmail());
		customer.setCity(customerDTO.getCity());
		/*
		 * if (customerDTO.getProducts() != null && customerDTO.getProducts().size() >
		 * 0) {
		 * customer.setProducts(customerDTO.getProducts().stream().map(productMapper::
		 * productDTOToProduct) .collect(Collectors.toList())); }
		 */
		return customer;

	}

}
