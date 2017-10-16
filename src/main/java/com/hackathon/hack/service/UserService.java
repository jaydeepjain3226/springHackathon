package com.hackathon.hack.service;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
   
   //regex for username validation
   public boolean validateUserName(String userName) {
       String regx = "[a-zA-Z0-9]+\\.?";
       Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
       Matcher matcher = pattern.matcher(userName);
       return matcher.find();
   }
   
   //regex for email validation
   public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
           Pattern.CASE_INSENSITIVE);

   //function for email validation
   public boolean validate(String emailStr) {
       Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
       return matcher.find();
   }

    
   
}
 