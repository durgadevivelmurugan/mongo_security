package com.mon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mon.filter.JWTAuthenticationFilter;
import com.mon.serviceimpl.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	JWTAuthenticationFilter authenticationFilter;
	
	@Autowired
	 UserServiceImpl impl;  
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						req ->req.requestMatchers("/login/**","/register/**","/role/**").permitAll()
						.requestMatchers("/admin/**","/get").hasAnyAuthority("ADMIN")
						.requestMatchers("/user","/get").hasAnyAuthority("USER")
						.anyRequest().authenticated())
					.userDetailsService(impl)
					.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
					.build();
								
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
