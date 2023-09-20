package com.crud.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.crud.models.UserResgistraion;
import com.crud.repositories.UserResgistrationRepository;
import com.crud.responsewrapper.UserResgistreaytionRR;

@Service
public class UserResgistrationService {
	
	@Autowired
	UserResgistrationRepository userResgistrationRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public ResponseEntity<?> registerUser (UserResgistraion userResgistraion)
	{
		String username=userResgistraion.getUsername();
		String password=userResgistraion.getPassword();
		String Confpassword=userResgistraion.getConfpassword();
		String email=userResgistraion.getEmail();
		
		Boolean is_username_present=userResgistrationRepository.findByUsername(username).isPresent();
		Boolean is_email_present=userResgistrationRepository.findByEmail(email).isPresent();
		Boolean is_password_matched=password.equals(Confpassword);
		
		if(is_username_present)
		{
			throw new ResponseStatusException(HttpStatus.FOUND,"username is laerdy exist");
			
		}
		else if(is_email_present)
		{
			throw new ResponseStatusException(HttpStatus.FOUND,"email is laerdy exist");
		}
		else if(is_password_matched)
		{
			userResgistraion.setPassword(passwordEncoder.encode(password));
			UserResgistraion registred_user=userResgistrationRepository.save(userResgistraion);
			UserResgistreaytionRR urw = new UserResgistreaytionRR();
			urw.setMessage("Following user Registred successfully");
			urw.setData(registred_user);
			return new ResponseEntity<>(urw,HttpStatus.CREATED);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"password is not matched");
		}
	}
	
	

}
