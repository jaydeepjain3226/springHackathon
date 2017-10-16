package com.hackathon.hack.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class UserProfile {
	
     @Id
	//	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String userName;
	private String emailId;
	
	
	//default constructor
	public UserProfile() {
		
	}
	
	//Constructor
	public UserProfile(int id, String name, String userName, String emailId) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.emailId = emailId;
	}
	
	//getters and setters of fields
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	

}
