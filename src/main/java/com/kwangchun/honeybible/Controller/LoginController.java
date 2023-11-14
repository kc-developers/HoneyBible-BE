package com.kwangchun.honeybible.Controller;

import com.kwangchun.honeybible.Service.LoginService;
import com.kwangchun.honeybible.dto.LoginRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Login", description = "Login 에 사용되는 API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        logger.info("[POST] login");

        String jwt = loginService.authenticateUser(loginRequest.getTtolae(), loginRequest.getUsername(), loginRequest.getLastPhoneNum());

        return ResponseEntity.ok(jwt);
    }
}
