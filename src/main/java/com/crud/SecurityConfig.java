package com.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.crud.sevice.UserLoginService;

import jakarta.servlet.DispatcherType;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public UserDetailsService userDetailsService()
//	{
//		UserDetails user1= User.builder().username("arjun").password(passwordEncoder().encode("arjun@123")).roles("MANAGER").build();
//		UserDetails user2=User.builder().username("sanika").password (passwordEncoder().encode("sanika@123")).roles("SUPERVISOR").build();
//		UserDetails user3=User.builder().username("sidd").password(passwordEncoder().encode("sid@123")).roles("GAURD").build();
//		
//		return new InMemoryUserDetailsManager(user1,user2,user3);
//	}
	
	@Autowired
	UserLoginService userLoginService;
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider .setUserDetailsService(userLoginService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		//can take any request apis
		//http.authorizeHttpRequests(http_request -> http_request.anyRequest().permitAll());
		http.csrf(http_request-> http_request.disable());
		
//		http.authorizeHttpRequests(http_request -> http_request.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());
		
		http.authorizeHttpRequests(http_request -> http_request
				                                              .dispatcherTypeMatchers(DispatcherType.FORWARD,DispatcherType.ERROR).permitAll()
				                                              .requestMatchers(HttpMethod.POST,"/products").hasAnyAuthority("MANAGER")
				                                              .requestMatchers(HttpMethod.POST,"/users/register").permitAll()
				                                              .anyRequest()
				                                              .authenticated())
		                                                      .httpBasic(Customizer.withDefaults()).authenticationProvider(daoAuthenticationProvider());
		
		return http.build();
		
	}
	
	
}
