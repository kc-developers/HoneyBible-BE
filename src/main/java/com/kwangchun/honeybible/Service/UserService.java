package com.kwangchun.honeybible.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.kwangchun.honeybible.Repository.UserRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String getUserList() {
        return userRepository.findAll();
    }
}
