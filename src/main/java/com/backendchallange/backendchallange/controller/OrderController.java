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

import com.backendchallange.backendchallange.dto.DateDto;
import com.backendchallange.backendchallange.model.Order;
import com.backendchallange.backendchallange.service.OrderService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "OrderApi")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/order/get")
	public ResponseEntity<List<Order>> getAllOrders() {
		return orderService.getAllOrders();
	}
	
	@PostMapping("/order/find")
	public ResponseEntity<List<Order>> findByCreatedTimeGreaterThan(@RequestBody final DateDto dateDto){
		return orderService.findByCreatedTimeGreaterThan(dateDto.getDate());
	}

	@PostMapping("/order/create")
	public ResponseEntity<String> createOrder(@RequestBody final Order order) {
		return orderService.createOrder(order);
	}

	@PutMapping("/order/update/{id}")
	public ResponseEntity<String> updateOrder(@RequestBody final Order order, @PathVariable final Long id) {
		return orderService.updateOrder(order,id);
	}
	@DeleteMapping("/order/delete/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable final Long id) {
		return orderService.deleteOrder(id);
	}
}
