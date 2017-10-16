package com.hackathon.hack.service;

import java.io.InvalidObjectException;
import java.util.List;

import com.hackathon.hack.domain.UserProfile;

public interface UserServiceInterface {
	
	public UserProfile addUser(UserProfile user);
	public List<UserProfile> getAllUserProfiles( );
	public void deleteUserProfile(int id);
	public UserProfile findById(int id);
	public void updateUserProfile(UserProfile user);

}
