package com.kwangchun.honeybible.Repository;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ReadcheckRepository {

    private final JdbcTemplate jdbcTemplate;

    private final Gson gson;

    public String selectAll() {
    	
      List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM TB_READCHECK");

      return gson.toJson(results);
    }
    
    public String selectOne(String dateKey) {
        String query = "SELECT * FROM TB_READCHECK WHERE READ_NUM = ?";
        
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(query, dateKey);
            return gson.toJson(result);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
}
