package com.hackathon.hack.controller;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import com.hackathon.hack.App;
import com.hackathon.hack.domain.UserProfile;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest  extends TestCase {
   
    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    UserProfile userProfile;    
   
    @Before
    public void setUp() throws Exception {
    	userProfile = new UserProfile(1,"NameTest","UserNameTest","emailid@server.com");
    }
    
   private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
    
   @After
    public void tearDown() throws Exception {
    }
    
   @Test
    public void addUserProfile() throws Exception { 
       HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserProfile> entity = new HttpEntity<UserProfile>(userProfile, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/v1.0/userservice/userprofile"),
                HttpMethod.POST, entity, String.class);
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("User profile added successfully",actual);
    }
    
   @Test
    public void getAllUsers() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/v1.0/userservice/userprofile"),
                HttpMethod.GET, entity, String.class);
        assertNotNull(response);
    }
    
   @Test
    public void testGetUser() throws Exception {
    }
    
   @Test
    public void updateUser() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserProfile> entity = new HttpEntity<UserProfile>(userProfile, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/v1.0/userservice/userprofile/2"),
                HttpMethod.PUT, entity, String.class);
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("User Updated Successfully",actual);
    }
    
   @Test
    public void deleteUser() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserProfile> entity = new HttpEntity<UserProfile>(userProfile, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/v1.0/userservice/userprofile/1"),
                HttpMethod.DELETE, entity, String.class);
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("User Profile deleted successfully",actual);
    }
    
}