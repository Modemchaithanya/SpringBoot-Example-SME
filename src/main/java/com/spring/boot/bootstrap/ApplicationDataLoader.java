package com.spring.boot.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.boot.dto.CustomerDTO;
import com.spring.boot.dto.ProductDTO;
import com.spring.boot.service.CustomerService;
import com.spring.boot.service.ProductService;

@Component
public class ApplicationDataLoader implements CommandLineRunner {
	private CustomerService customerService;
	private ProductService productService;

	public ApplicationDataLoader(final CustomerService customerService, final ProductService productService) {
		this.customerService = customerService;
		this.productService = productService;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Loading data");
		loadAggregateData();

	}

	private void loadAggregateData() {
		CustomerDTO cust1 = new CustomerDTO();
		cust1.setId(null);
		cust1.setFirstName("Steven");
		cust1.setLastName("King");
		cust1.setEmail("king@gmail.com");
		cust1.setCity("Hyderabad");
		cust1 = customerService.createCustomer(cust1);

		ProductDTO laptop = new ProductDTO();
		laptop.setId(1L);
		laptop.setName("Laptop");
		laptop.setDescription("LAPTOP");
		laptop.setPrice(new BigDecimal(1000));
		laptop.setCustomer(cust1);
		productService.createProduct(laptop);

		ProductDTO tablet = new ProductDTO();
		tablet.setId(2L);
		tablet.setName("Tablet");
		tablet.setDescription("Tablet");
		tablet.setPrice(new BigDecimal(500));
		tablet.setCustomer(cust1);
		productService.createProduct(tablet);

	}

	private void loadCustomerData() {
		// TODO Auto-generated method stub
		CustomerDTO cust1 = new CustomerDTO();
		cust1.setId(null);
		cust1.setFirstName("Steven");
		cust1.setLastName("King");
		cust1.setEmail("king@gmail.com");
		cust1.setCity("Hyderabad");
		customerService.createCustomer(cust1);

		CustomerDTO cust2 = new CustomerDTO();
		cust2.setId(null);
		cust2.setFirstName("Neena");
		cust2.setLastName("Kochhar");
		cust2.setEmail("kochhar@gmail.com");
		cust2.setCity("Pune");
		customerService.createCustomer(cust2);

		CustomerDTO cust3 = new CustomerDTO();
		cust3.setId(null);
		cust3.setFirstName("John");
		cust3.setLastName("Chen");
		cust3.setEmail("johnr@gmail.com");
		cust3.setCity("Bangalore");
		customerService.createCustomer(cust3);

		CustomerDTO cust4 = new CustomerDTO();
		cust4.setId(null);
		cust4.setFirstName("Nancy");
		cust4.setLastName("Greenberg");
		cust4.setEmail("nancy@gmail.com");
		cust4.setCity("Mumbai");
		customerService.createCustomer(cust4);

		CustomerDTO cust5 = new CustomerDTO();
		cust5.setId(5L);
		cust5.setFirstName("Luis");
		cust5.setLastName("Popp");
		cust5.setEmail("popp@gmail.com");
		cust5.setCity("Delhi");
		customerService.createCustomer(cust5);

	}

	private void loadProductData() {
		ProductDTO laptop = new ProductDTO();
		laptop.setId(1L);
		laptop.setName("Laptop");
		laptop.setDescription("LAPTOP");
		laptop.setPrice(new BigDecimal(1000));
		productService.createProduct(laptop);

		ProductDTO tablet = new ProductDTO();
		tablet.setId(2L);
		tablet.setName("Tablet");
		tablet.setDescription("Tablet");
		tablet.setPrice(new BigDecimal(500));
		productService.createProduct(tablet);

		ProductDTO mobile = new ProductDTO();
		mobile.setId(3L);
		mobile.setName("Mobile");
		mobile.setDescription("Mobile");
		mobile.setPrice(new BigDecimal(500));
		productService.createProduct(mobile);

		ProductDTO pendrive = new ProductDTO();
		pendrive.setId(4L);
		pendrive.setName("Pendrive");
		pendrive.setDescription("Pendrive");
		pendrive.setPrice(new BigDecimal(10));
		productService.createProduct(pendrive);

		ProductDTO car = new ProductDTO();
		car.setId(5L);
		car.setName("Car");
		car.setDescription("Car");
		car.setPrice(new BigDecimal(1000));
		productService.createProduct(car);
	}

}
