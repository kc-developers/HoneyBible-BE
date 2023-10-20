package com.kwangchun.honeybible.Service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.kwangchun.honeybible.Repository.UserRepository;
import com.kwangchun.honeybible.dto.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final Gson gson;

    public String getAllUsers() {
        return gson.toJson(userRepository.selectAll());
    }

    public String getUser(String ttolae, String name) {
    	return gson.toJson(userRepository.selectOne(name, ttolae));
    }

    public String getMemberNum(String ttolae, String name) {
    	Map<String, Object> user = userRepository.selectOne(name, ttolae);
    	return user.get("MEMBER_NUM").toString();
    }
    
    public String createUser(User user) {
        List<Map<String, Object>> allUsers = userRepository.selectAll();

        int maxMemberNum = 0;
        for (Map<String, Object> oneUser : allUsers) {
        	int memberNum = Integer.parseInt(oneUser.get("MEMBER_NUM").toString());
        	if (memberNum > maxMemberNum) {
        		maxMemberNum = memberNum;
        	}
        }
        user.setMemberNum(Integer.toString(maxMemberNum + 1));

    	return gson.toJson(userRepository.createUser(user));
    }
   
    public String alterUserInfo(String ttolae, String name, String key, String value) {
        String memberNum = getMemberNum(ttolae, name);

        Map<String, Object> user = userRepository.selectOne(name, ttolae);

        Map<String, String> result = new HashMap<>();
        result.put("oldName", user.get("NAME").toString());
        result.put("oldTtolae", user.get("TTOLAE").toString());

        userRepository.alterUserInfo(memberNum, key, value);

        if (key.equals("ttolae")) {
            ttolae = value;
        } else if (key.equals("name")) {
            name = value;
        }
        user = userRepository.selectOne(name, ttolae);
        result.put("newName", user.get("NAME").toString());
        result.put("newTtolae", user.get("TTOLAE").toString());

    	return gson.toJson(result);
    }
    
    public String deleteUser(String ttolae, String name) {
        Map<String, Object> user = userRepository.selectOne(name, ttolae);

        String memberNum = getMemberNum(ttolae, name);
        userRepository.deleteUser(memberNum);
    	
    	return gson.toJson(user);
    }
}
