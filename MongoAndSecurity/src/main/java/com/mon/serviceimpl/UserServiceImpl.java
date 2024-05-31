package com.mon.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mon.repository.AggregateRepo;

@Service
public class UserServiceImpl implements UserDetailsService{

	@Autowired
	AggregateRepo aggregateRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return aggregateRepo.findByUsername(username)
				.orElseThrow (()-> new UsernameNotFoundException("user not found"));
		
		//if(List<Aggregationex>==>it show error in 21st line(because in repo want to mention Optional<AggregateEx>)
		
		 
	}

}
