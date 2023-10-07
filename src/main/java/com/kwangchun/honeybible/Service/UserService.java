package com.kwangchun.honeybible.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.kwangchun.honeybible.Repository.UserRepository;
import com.kwangchun.honeybible.dto.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import java.util.Date;

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
    	userRepository.updateUserUpdateDt(memberNum);

    	return userRepository.alterUserInfo(memberNum, key, value);
    }
    
    public String deleteUser(String memberNum) {
    	
    	return userRepository.deleteUser(memberNum);
    }
}
