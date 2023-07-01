package com.backendchallange.backendchallange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backendchallange.backendchallange.model.Customer;
import com.backendchallange.backendchallange.service.CustomerService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "CustomerApi")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customer/get")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	@GetMapping("/customer/find/empty/order")
	public ResponseEntity<List<Customer>> findCustomersWithoutOrders(){
		return customerService.findCustomersWithoutOrders();
	}
	@GetMapping("/customer/find/{name}")
	public ResponseEntity<List<String>> findCustomersByName(@PathVariable final String name){
		return customerService.findCustomersByName(name);
	}
	
	@PostMapping("/customer/create")
	public ResponseEntity<String> createCustomer(@RequestBody final Customer customer) {
		return customerService.createCustomer(customer);
	}
	
	@PutMapping("/customer/update/{id}")
	public ResponseEntity<String> updateCustomer(@RequestBody final Customer customer,@PathVariable final Long id){
		return customerService.updateCustomer(customer, id);
	}
	
	@DeleteMapping("/customer/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable final Long id){
		return customerService.deleteCustomer(id);
	}
}
