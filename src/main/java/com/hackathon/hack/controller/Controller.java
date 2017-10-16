package com.hackathon.hack.controller;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.hack.domain.UserProfile;
import com.hackathon.hack.service.UserServiceInterface;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="onlinestore", description="User Profiles")
@RequestMapping("/v1.0/userservice")
public class Controller {
	// @Autowired
	// private int res_id;
	//
	//
	//
	// public int getRes_id() {
	// return res_id;
	// }
	//
	//
	//
	// public void setRes_id(int res_id) {
	// this.res_id = res_id;
	// }

	@Autowired
	private UserServiceInterface userService;
	

	
//	@RequestMapping(value="/userprofiles",produces = "application/json",method = RequestMethod.GET)
    @ApiOperation(value = "View a list of available users", response = Iterable.class)
	@RequestMapping(value="/userprofile",produces = "application/json",method = RequestMethod.GET)
	public ResponseEntity getAllUserProfiles() {
		List<UserProfile> resultList1 = userService.getAllUserProfiles();

		return new ResponseEntity<List<UserProfile>>(resultList1, HttpStatus.OK);
	}

	@RequestMapping(value = "/userprofile/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserProfile> getUserProfileById(@PathVariable("id") Integer id) {

		// System.out.println("1");

		UserProfile user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<UserProfile>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserProfile>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/userprofile")
	public ResponseEntity addRestaurant(@RequestBody UserProfile user) {
		/* Add validation code */
		if(userService.validateUserName(user.getUserName())) {
			
			if (userService.validate(user.getEmailId())) {
				userService.addUser(user);
				return new ResponseEntity<String>("User profile added successfully", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("Valid email is required", HttpStatus.EXPECTATION_FAILED);
			}
		}
		else {
			return new ResponseEntity<String>("Valid username is required", HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/userprofile/{id}")
	public ResponseEntity deleteUser(@PathVariable("id") Integer id) {
		
		if(userService.findById(id) != null) {
			userService.deleteUserProfile(id);
			return new ResponseEntity<String>("User Profile deleted successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);
		}
	}


	@RequestMapping(value = "/userprofile/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUserProfile(@RequestBody UserProfile user, @PathVariable int  id ) {
		if(userService.findById(id) != null) {
			userService.updateUserProfile(user);
			return new ResponseEntity<String>("User Updated Successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("User ID not found", HttpStatus.NOT_FOUND);
		}
	}

}