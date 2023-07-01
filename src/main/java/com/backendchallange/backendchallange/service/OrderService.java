package com.backendchallange.backendchallange.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backendchallange.backendchallange.model.Order;
import com.backendchallange.backendchallange.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	public ResponseEntity<List<Order>> getAllOrders() {
		return ResponseEntity.status(HttpStatus.FOUND).body(orderRepository.findAll());
	}

	public ResponseEntity<String> createOrder(final Order order) {
		try {
			orderRepository.save(order);
			return ResponseEntity.status(HttpStatus.CREATED).body(order.toString() + " Başarı ile kayıt edilmiştir.");
		} catch (final Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kayıt yaratılırken bir hata meydana gelmiştir.");
		}
	}

	public ResponseEntity<String> updateOrder(final Order order, final Long id) {
		final Optional<Order> foundOrderOpt = orderRepository.findById(id);
		if (foundOrderOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Belirtilen id'li personel bulunamamıştır.");
		} else {
			final Order foundOrder = foundOrderOpt.get();
			foundOrder.setCreatedTime(order.getCreatedTime());
			foundOrder.setCustomer(order.getCustomer());
			foundOrder.setTotalPrice(order.getTotalPrice());
			orderRepository.save(foundOrder);

			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(foundOrder.getOrderId().toString() + " id 'li kayıt güncellenmiştir.");

		}

	}

	public ResponseEntity<String> deleteOrder(final Long id) {
		final Optional<Order> foundOrderOpt = orderRepository.findById(id);
		if (foundOrderOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Belirtilen id'li personel bulunamamıştır.");
		} else {
			orderRepository.delete(foundOrderOpt.get());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Seçilen Kayıt Başarı ile Silinmiştir.");
		}
	}

	public ResponseEntity<List<Order>> findByCreatedTimeGreaterThan(final LocalDate date) {
		return ResponseEntity.status(HttpStatus.FOUND).body(orderRepository.findByCreatedTimeGreaterThan(date));
	}

}
