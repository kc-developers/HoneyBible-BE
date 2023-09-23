package com.kwangchun.honeybible.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    	jdbcTemplate.update(sql, user.getMember_num(), user.getName(), user.getTtolae(), user.getBirth_date(), user.getInsert_dt(), user.getUpdate_dt(), user.getMember_auth(), user.getStatus());
    	return "";
    }
    
    public String alterUserInfo(String memberNum, String key, String value) {
    	
        String sql = "UPDATE TB_MEMBER SET " + key + " = ? WHERE MEMBER_NUM = ?";

        try {
            jdbcTemplate.update(sql, value, memberNum);
            return "User info updated successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to update user info.";
        }
    }
}
