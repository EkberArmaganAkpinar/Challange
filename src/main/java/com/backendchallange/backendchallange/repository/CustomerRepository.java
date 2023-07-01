package com.backendchallange.backendchallange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backendchallange.backendchallange.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
     
	 @Query("SELECT c FROM Customer c WHERE c.orders IS EMPTY")
	    List<Customer> findCustomersWithoutOrders();
	 
	 @Query("SELECT c FROM Customer c WHERE c.name LIKE %:name")
	    List<Customer> findCustomersByName(final String name);
}
