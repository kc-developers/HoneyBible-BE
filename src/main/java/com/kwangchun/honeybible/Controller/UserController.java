package com.kwangchun.honeybible.Controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kwangchun.honeybible.Service.UserService;
import com.kwangchun.honeybible.dto.User;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping("/all")
    public String getAllUsers() {
        logger.info("[GET] all users");

        String allUsers = userService.getAllUsers();
        logger.info("- all users : {}", allUsers);

        return allUsers;
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
    	logger.info("[POST] create user");
    	logger.info("new user info : {}", user.toString());

    	return userService.createUser(user);
    }
    
    @PutMapping("/{memberNum}")
    public String alterUser(@PathVariable String memberNum, @RequestParam String key, @RequestParam String value) {
        logger.info("[Put] alter user info");
        logger.info("- new user info = {} : {}", key, value);
        
        return userService.alterUserInfo(memberNum, key, value);
    }
    
    @DeleteMapping("/{memberNum}")
    public String deleteUser(@PathVariable String memberNum) {
        logger.info("[Delete] user : {}", memberNum);
        
        return userService.deleteUser(memberNum);
    }
}
