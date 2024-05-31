package com.mon.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mon.entity.AggregationEx;
import com.mon.entity.AuthenticationResponse;
import com.mon.repository.AggregateRepo;

@Service
public class AuthenticationService {
 
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtService jwtService;
	@Autowired
	AggregateRepo aggregateRepo;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	public AuthenticationResponse register(AggregationEx register) {
		AggregationEx agg=new AggregationEx();
		agg.setId(register.getId());
		agg.setPassword(encoder.encode(register.getPassword()));
		agg.setPincode(register.getPincode());
		agg.setPlace(register.getPlace());
		agg.setUsername(register.getUsername());
		agg.setRole(register.getRole());
		
		aggregateRepo.save(agg);
		
		String token=jwtService.generateToken(agg);
		return new AuthenticationResponse(token);
	}
	public AuthenticationResponse login(AggregationEx aggregationEx) {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(aggregationEx.getUsername(), aggregationEx.getPassword()));
			aggregateRepo.findByUsername(aggregationEx.getUsername());
			String token =jwtService.generateToken(aggregationEx);
			return new AuthenticationResponse(token);
		}
	}

