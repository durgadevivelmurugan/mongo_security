package com.mon.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mon.serviceimpl.JwtService;
import com.mon.serviceimpl.UserServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserServiceImpl impl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {

		
		String authHeader=request.getHeader("Authorization");
		
		if(authHeader ==null||! authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token=authHeader.substring(7);
		String username=jwtService.extractUsername(token);
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails details=impl.loadUserByUsername(username);
			
			if(jwtService.isValid(token, details)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(details, null,details.getAuthorities());
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			}
		} 
		filterChain.doFilter(request, response);
	}

}
