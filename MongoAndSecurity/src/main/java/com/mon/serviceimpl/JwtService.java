package com.mon.serviceimpl;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mon.entity.AggregationEx;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final String SECRETKEY="e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
	
	public String generateToken(AggregationEx aggregationEx) {
		 
		String token=Jwts.builder()
				.subject(aggregationEx.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
				.signWith(signinkey())
				.compact();
		
		return token;
	}

	private SecretKey signinkey() {
		
		byte[] keybytes=Decoders.BASE64URL.decode(SECRETKEY);
		
		return Keys.hmacShaKeyFor(keybytes);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parser()//converting data from one format to another(parser)
				.verifyWith(signinkey()) 
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	//T=type,<T>=generic type(return type)
	public <T>T extractClaim(String token,Function<Claims, T>resolver){
		Claims claims=extractAllClaims(token);
		return resolver.apply(claims);
	
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
		
	}
	
	public boolean isValid(String token,UserDetails details) {
		
		String username=extractUsername(token);
		return (username.equals(details.getUsername()))&& !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
}
