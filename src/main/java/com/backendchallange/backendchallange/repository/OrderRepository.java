package com.backendchallange.backendchallange.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendchallange.backendchallange.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByCreatedTimeGreaterThan(final LocalDate givenDate);
}
