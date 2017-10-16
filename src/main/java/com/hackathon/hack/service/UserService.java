package com.hackathon.hack.service;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.hack.domain.UserProfile;
import com.hackathon.hack.repository.UserRepository;


@Service
public class UserService implements UserServiceInterface{
	
	@Autowired
	private UserRepository userRepository;
    
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

    
	
	//method to retrieve all users 
    public List<UserProfile> getAllUserProfiles( )
    {
    	List<UserProfile> userProfileList = (List<UserProfile>) userRepository.findAll();
        return userProfileList;
    }
    
    
    //method to add user to the list
    public UserProfile addUser(UserProfile user)
    {
    	userRepository.save(user);
    	return user;
   
    }
    
    //method to delete user profile
    public void deleteUserProfile(int id)
    {	
    		
    		userRepository.delete(id);   	
    }
    
    //method to retrieve user profile by ID
    public UserProfile findById(int id) {

    	if(userRepository.findOne(id)==null)
    		return null;
    	else
    		return userRepository.findOne(id);
  	
    }  

   
   public void updateUserProfile(UserProfile user) {
       userRepository.save(user);
   }

    
   
}
 