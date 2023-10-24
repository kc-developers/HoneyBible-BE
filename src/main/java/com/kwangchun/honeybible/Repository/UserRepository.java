package com.kwangchun.honeybible.Repository;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.kwangchun.honeybible.dto.User;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> selectAll() {
    	
      List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM TB_MEMBER");

      return results;
    }

    public Map<String, Object> selectOne(String name, String ttolae) {
        String sql = "SELECT * FROM TB_MEMBER WHERE NAME = ? AND TTOLAE = ?";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, name, ttolae);

        if (result != null && !result.isEmpty()) {
            return result;
        } else {
            throw new RuntimeException("No user found with name : " + name + " and ttolae : " + ttolae);
        }
    }
    
    public Map<String, String> createUser(User user) {
        String sql = "INSERT INTO TB_MEMBER(MEMBER_NUM, NAME, TTOLAE, BIRTH_DATE, INSERT_DT, UPDATE_DT, MEMBER_AUTH, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, user.getMemberNum(), user.getName(), user.getTtolae(), user.getBirthDate(), user.getInsertDt(), user.getUpdateDt(), user.getMemberAuth(), user.getStatus());

        Map<String, String> response = new HashMap<>();
        response.put("message", "New User created successfully.");
        return response;
    }
    
    public Map<String, String> alterUserInfo(String memberNum, String key, String value) {
        String sql = "UPDATE TB_MEMBER SET " + key + " = ? WHERE MEMBER_NUM = ?";

        jdbcTemplate.update(sql, value, memberNum);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User info updated successfully or nothing change");
        return response;
    }

    public void updateUserUpdateDt(String memberNum) {
    	LocalDateTime now = LocalDateTime.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    	String nowString = now.format(formatter);

    	String sql = "UPDATE TB_MEMBER SET UPDATE_DT = TO_DATE(?,'yyyymmddhh24miss') WHERE MEMBER_NUM = ?";
	
        jdbcTemplate.update(sql, nowString, memberNum);
	}
    
    public Map<String, String> deleteUser(String memberNum) {
        String sql = "DELETE FROM TB_MEMBER WHERE MEMBER_NUM = ?";

        int rowsAffected = jdbcTemplate.update(sql, memberNum);

        Map<String, String> response = new HashMap<>();

        if (rowsAffected > 0) {
            response.put("message", "User deleted successfully.");
            return response;
        } else {
            response.put("message", "User not found or deletion failed.");
            return response;
        }
    }
}
