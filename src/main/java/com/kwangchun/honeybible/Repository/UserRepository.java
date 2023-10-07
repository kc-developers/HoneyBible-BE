package com.kwangchun.honeybible.Repository;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.kwangchun.honeybible.dto.User;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final Gson gson;

    public String selectAll() {
    	
      List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM TB_MEMBER");

      return gson.toJson(results);
    }
    
    public String createUser(User user) {
        String sql = "INSERT INTO TB_MEMBER(MEMBER_NUM, NAME, TTOLAE, BIRTH_DATE, INSERT_DT, UPDATE_DT, MEMBER_AUTH, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
        	jdbcTemplate.update(sql, user.getMember_num(), user.getName(), user.getTtolae(), user.getBirth_date(), user.getInsert_dt(), user.getUpdate_dt(), user.getMember_auth(), user.getStatus());
        	return "New User created successfully.";
        } catch (Exception e) {
        	return e.getMessage();
		}
    	
    }
    
    public String alterUserInfo(String memberNum, String key, String value) {
    	
        String sql = "UPDATE TB_MEMBER SET " + key + " = ? WHERE MEMBER_NUM = ?";

        try {
            jdbcTemplate.update(sql, value, memberNum);
            return "User info updated successfully or nothing change";
        } catch (Exception e) {
        	return e.getMessage();
        }
    }

    public void updateUserUpdateDt(String memberNum) {
    	LocalDateTime now = LocalDateTime.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    	String nowString = now.format(formatter);

    	String sql = "UPDATE TB_MEMBER SET UPDATE_DT = TO_DATE(?,'yyyymmddhh24miss') WHERE MEMBER_NUM = ?";
	
        jdbcTemplate.update(sql, nowString, memberNum);
	}
    
    public String deleteUser(String memberNum) {
        String sql = "DELETE FROM TB_MEMBER WHERE MEMBER_NUM = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, memberNum);

            if (rowsAffected > 0) {
                return "User deleted successfully.";
            } else {
                return "User not found or deletion failed.";
            }
        } catch (Exception e) {
        	return e.getMessage();
        }
    }
}
