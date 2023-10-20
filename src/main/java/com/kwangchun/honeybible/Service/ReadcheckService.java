package com.kwangchun.honeybible.Service;

import com.google.gson.Gson;
import com.kwangchun.honeybible.Repository.ReadcheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReadcheckService {

    private final ReadcheckRepository readcheckRepository;

    private final Gson gson;

    public String getReadchecks() {
        return gson.toJson(readcheckRepository.selectAll());
    }
    
    public String getReadcheck(String memberNum, String dateKey) {
        boolean read = true;
        try {
            readcheckRepository.selectOne(dateKey, memberNum);
        } catch (EmptyResultDataAccessException e) {
            read = false;
        }

        return gson.toJson(read);
    }
    
    public String getReadcheck(String dateKey) {
    	return readcheckRepository.selectOne(dateKey);
    }
    
}
