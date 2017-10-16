package com.hackathon.hack.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.hackathon.hack.domain.UserProfile;


@Component
public interface UserRepository extends CrudRepository<UserProfile, Integer>{

}

