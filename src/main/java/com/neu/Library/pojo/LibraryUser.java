package com.neu.Library.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class LibraryUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private long userId;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String email;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	
	@Column
	private String role;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="userLibraryId")
	private LibraryUserAccount libUserAcc;
	
	public LibraryUser() {
		
	}
	


	public long getUserId() {
		return userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public LibraryUserAccount getLibUserAcc() {
		return libUserAcc;
	}

	public void setLibUserAcc(LibraryUserAccount libUserAcc) {
		this.libUserAcc = libUserAcc;
	}
	
	
	
	
}
