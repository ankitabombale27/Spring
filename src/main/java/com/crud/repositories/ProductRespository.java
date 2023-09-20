package com.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.models.Product;

public interface ProductRespository  extends JpaRepository<Product, Integer>{

}
