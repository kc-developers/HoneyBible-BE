package com.kwangchun.honeybible.Repository;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import com.google.gson.Gson;

@Repository
@RequiredArgsConstructor
public class CalendarRepository {

    private final JdbcTemplate jdbcTemplate;

    private final Gson gson;

    public String selectAll() {
    	
      List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM TB_CALENDAR");

      return gson.toJson(results);
    }
    
    public String selectOne(String dateKey) {
        String query = "SELECT * FROM TB_CALENDAR WHERE DATE_KEY = ?";
        
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(query, dateKey);
            return gson.toJson(result);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
}
