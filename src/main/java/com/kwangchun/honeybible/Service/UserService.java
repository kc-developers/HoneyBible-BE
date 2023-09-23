package com.kwangchun.honeybible.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.kwangchun.honeybible.Repository.UserRepository;
import com.kwangchun.honeybible.dto.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String getAllUsers() {
        return userRepository.selectAll();
    }
    
    public String createUser(User user) {

    	return userRepository.createUser(user);
    }
   
    public String alterUserInfo(String memberNum, String key, String value) {
    	
    	return userRepository.alterUserInfo(memberNum, key, value);
    }
}
