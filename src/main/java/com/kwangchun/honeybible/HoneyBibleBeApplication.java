package com.kwangchun.honeybible;

import java.util.List;
import java.util.Map;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class HoneyBibleBeApplication {

    private final JdbcTemplate jdbcTemplate;

    public HoneyBibleBeApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(HoneyBibleBeApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM TB_MEMBER");

        for (Map<String, Object> row : results) {
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                String column = entry.getKey();
                Object value = entry.getValue();
                System.out.println(column + ": " + value);
            }
            System.out.println("----------------------");
        }    	
    	
        return args -> {};
    }
}
