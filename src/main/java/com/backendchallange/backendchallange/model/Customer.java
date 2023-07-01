package com.backendchallange.backendchallange.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customers")
@ToString
public class Customer {
	
	@Id
	@Column(name ="customer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
    @Column(name="name")
	private String name;
    
    @Column(name="age")
	private Integer age;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="fk_customer_id",referencedColumnName = "customer_id")
	private List<Order> orders;
  
}
