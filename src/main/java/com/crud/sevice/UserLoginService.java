package com.crud.sevice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crud.models.UserResgistraion;
import com.crud.repositories.UserResgistrationRepository;

@Service
public class UserLoginService implements UserDetailsService {
	
	@Autowired
	UserResgistrationRepository userResgistrationRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserResgistraion foundUser =userResgistrationRepository.findByUsername(username).orElseThrow(
				()->
				{
					throw new UsernameNotFoundException ("Given username does not exist");
				}
				);
		
		String userName=foundUser.getUsername();
		String userPassword=foundUser.getPassword();
	    List<String> userRoles= foundUser.getRoles();
	    
	    Collection< GrantedAuthority>authorites= new ArrayList<>();
	    
	    for(String role:userRoles)
	    {
	    	authorites.add(new SimpleGrantedAuthority(role));
	    }
	    
	    UserResgistraion userResgistraion=new UserResgistraion(userName, userPassword, authorites);

		return userResgistraion;
	}
	
	

}
