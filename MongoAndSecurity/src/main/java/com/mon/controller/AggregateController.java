package com.mon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mon.entity.AggregationEx;
import com.mon.entity.AuthenticationResponse;
import com.mon.repository.AggregateRepo;
import com.mon.serviceimpl.AuthenticationService;

@RestController
public class AggregateController {

	
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	AggregateRepo aggregateRepo;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody AggregationEx aggregationEx){
		return ResponseEntity.ok(authenticationService.register(aggregationEx));
	}
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AggregationEx aggregationEx){
		return ResponseEntity.ok(authenticationService.login(aggregationEx));
	}
	
	@GetMapping("/get")
	public List<AggregationEx> getall(){
		return aggregateRepo.findAll();
		
	
	}
	@GetMapping("/admin")
	public ResponseEntity<String> getadmin(){
		return ResponseEntity.ok("Hello,Admin");
	}
	@GetMapping("/user")
	public ResponseEntity<String> getUser(){
		return ResponseEntity.ok("Hello,User");
	}
	
	
	
}
