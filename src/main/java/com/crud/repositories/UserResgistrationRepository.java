package com.crud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.crud.models.UserResgistraion;



public interface UserResgistrationRepository  extends JpaRepository<UserResgistraion, Integer> {
	
	@RestResource(exported = false)
	<S extends UserResgistraion> S save(S entity);
	
	@RestResource
	Optional<UserResgistraion>  findByUsername(String username);
	
	@RestResource
	Optional<UserResgistraion>  findByEmail(String email);
	
	

}
