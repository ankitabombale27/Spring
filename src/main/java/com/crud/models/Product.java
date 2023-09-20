package com.crud.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	@Size(min=5,max=10,message="enter name between 5-10 charcaters")
	private String name;
	
	
	@Min(value=0, message="min price must be zero")
	
	private Double price;
	
	@Column(nullable = false)
	@Size(min=5,max=10,message="enter category between 5-10 charcaters")
	private String category;
	
	

}
