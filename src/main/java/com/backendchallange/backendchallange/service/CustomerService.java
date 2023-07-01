package com.backendchallange.backendchallange.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.backendchallange.backendchallange.model.Customer;
import com.backendchallange.backendchallange.model.Order;
import com.backendchallange.backendchallange.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.status(HttpStatus.FOUND).body(customerRepository.findAll());
	}

	public ResponseEntity<String> createCustomer(final Customer customer) {
		try {
			customerRepository.save(customer);
			return ResponseEntity.status(HttpStatus.CREATED).body(customer.toString()+"Başarı ile kayıt edildi.");
			}
			catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kayıt yaratılırken bir hata meydana gelmiştir.");
			}
	}
	
	public ResponseEntity<String> updateCustomer(final Customer customer,final Long id){
		final Optional<Customer> foundCustomerOpt = customerRepository.findById(id);
		if(foundCustomerOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id.toString()+"'li Customer Bulunamamıştır.");
		}else {
			final Customer foundCustomer = foundCustomerOpt.get();
			foundCustomer.setAge(customer.getAge());
			foundCustomer.setName(customer.getName());
			final List<Order> orders = customer.getOrders();
			if(!CollectionUtils.isEmpty(orders)) {				
				foundCustomer.setOrders(orders);
			}
			customerRepository.save(foundCustomer);
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(foundCustomer.getCustomerId().toString() + " id 'li kayıt güncellenmiştir.");
		}
		
	}
	 public ResponseEntity<String> deleteCustomer(final Long id){
			final Optional<Customer> foundCustomerOpt = customerRepository.findById(id);
			if (foundCustomerOpt.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Belirtilen id'li personel bulunamamıştır.");
			}else {
				customerRepository.delete(foundCustomerOpt.get());
				return ResponseEntity.status(HttpStatus.ACCEPTED)
						.body("Seçilen Kayıt Başarı ile Silinmiştir.");
			}
		 }

	public ResponseEntity<List<Customer>> findCustomersWithoutOrders() {
		return ResponseEntity.status(HttpStatus.FOUND).body(customerRepository.findCustomersWithoutOrders());
	}

	public ResponseEntity<List<String>> findCustomersByName(final String name) {
        final List<Customer> foundCustomers = customerRepository.findCustomersByName(name);
        if(CollectionUtils.isEmpty(foundCustomers)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of("İlgili isimde klayıtlar bulunamamıştır."));
        }else {
        	return ResponseEntity.status(HttpStatus.FOUND).body(getOrderIds(foundCustomers));
        }
	}

	private List<String> getOrderIds(final List<Customer> foundCustomers) {
		   return foundCustomers.stream()
			        .flatMap(customer -> customer.getOrders().stream()
			            .map(order -> customer.getCustomerId() + " customerın " + order.getOrderId() + " id'li orderı"))
			        .toList();
	}	
	
}
