package com.kwangchun.honeybible.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ReadcheckRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> selectAll() {
    	
      return jdbcTemplate.queryForList("SELECT * FROM TB_READCHECK");
    }
    
    public Map<String, Object> selectOne(String dateKey, String memberNum) {
        String query = "SELECT * FROM TB_READCHECK WHERE DATE_KEY = ? AND MEMBER_NUM = ?";
        
        return jdbcTemplate.queryForMap(query, dateKey, memberNum);
    }
    
}
