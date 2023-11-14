package com.kwangchun.honeybible.Service;

import com.kwangchun.honeybible.Repository.UserRepository;
import com.kwangchun.honeybible.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public String authenticateUser(String ttolae, String name, String lastPhoneNum) {

        Map<String, Object> user = userRepository.selectOne(name, ttolae);
        if (!Objects.equals(user.get("PHONE_NUMBER").toString().substring(7), lastPhoneNum)) {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.UNAUTHORIZED, "User authentication failed");
        }

        return jwtProvider.generateToken(name, ttolae, lastPhoneNum);
    }
}
