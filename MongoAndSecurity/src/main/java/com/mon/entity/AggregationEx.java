package com.mon.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document(collection ="students")
public class AggregationEx implements UserDetails {
	@Id
	private String ObjectId;
	private int id;
	private String username;
	private String place;
	private int pincode;
	private String password;
	private Role role;
	
	
	
	
	
	public AggregationEx(String objectId) {
		super();
		ObjectId = objectId;
	}





	public String getObjectId() {
		return ObjectId;
	}





	public void setObjectId(String objectId) {
		ObjectId = objectId;
	}





	public AggregationEx(int id, String username, String place, int pincode, String password, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.place = place;
		this.pincode = pincode;
		this.password = password;
		this.role = role;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}





	public String getPlace() {
		return place;
	}





	public void setPlace(String place) {
		this.place = place;
	}





	public int getPincode() {
		return pincode;
	}





	public void setPincode(int pincode) {
		this.pincode = pincode;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public Role getRole() {
		return role;
	}





	public void setRole(Role role) {
		this.role = role;
	}





	public AggregationEx() {
		super();
	}





	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}





	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}





	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}





	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}





	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
