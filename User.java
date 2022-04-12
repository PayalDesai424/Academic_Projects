package com.GroupG.beans;

import javax.validation.constraints.NotNull;

public class User {

	@NotNull(message = "Username cannot be blank")
	private String username;
	@NotNull(message = "Password cannot be blank")
	private String password;
	@NotNull(message="password can't be empty")
	private String email;
	private String fullname;
	private String address;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
