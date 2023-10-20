package com.kwangchun.honeybible.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RestController;

import com.kwangchun.honeybible.Service.UserService;
import com.kwangchun.honeybible.dto.User;

@Tag(name = "User", description = "TB_MEMBER 테이블에 대한 API")
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

    @GetMapping("/{ttolae}/{name}")
    public String getUser(@PathVariable String ttolae, @PathVariable String name) {
        logger.info("[GET] user");

        String user = userService.getUser(ttolae, name);
        logger.info("- user info : {}", user);

        return user;
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
    	logger.info("[POST] create user");
    	logger.info("new user info : {}", user.toString());

    	return userService.createUser(user);
    }

    @Operation(summary = "유저 데이터 수정", description = "수정 전 이름&또래, 수정 후 이름&또래를 반환한다.")
    @PutMapping("/{ttolae}/{name}/{key}/{value}")
    public String alterUser(@PathVariable String ttolae, @PathVariable String name, @PathVariable String key, @PathVariable String value) {
        logger.info("[Put] alter user info");
        logger.info("- ttolae : {}, name : {}, key : {}, value : {}", ttolae, name, key, value);

        return userService.alterUserInfo(ttolae, name, key, value);
    }

    @Operation(summary = "유저 데이터 삭제", description = "삭제한 유저의 정보를 반환한다.")
    @DeleteMapping("/{ttolae}/{name}")
    public String deleteUser(@PathVariable String ttolae, @PathVariable String name) {
        logger.info("[Delete] user : {} {}", ttolae, name);
        
        return userService.deleteUser(ttolae, name);
    }
}
