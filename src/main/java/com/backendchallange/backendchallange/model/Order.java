package com.backendchallange.backendchallange.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
@ToString
public class Order {
    @Id
    @Column(name ="order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
    
    @Column(name="created_time")
    private LocalDate createdTime;
    
    @Column(name="total_price")
    private BigDecimal totalPrice;
    
    @Column(name = "customer")
    private Long customer;
}
