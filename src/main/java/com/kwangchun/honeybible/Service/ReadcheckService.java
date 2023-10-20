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

    public String getDateReadBefore(String memberNum, String dateKey) {
        List<Map<String, Object>> readcheck = readcheckRepository.selectAll();

        List<String> dateKeys = new ArrayList<>();
        for (Map<String, Object> rc : readcheck) {
            if (rc.get("MEMBER_NUM").toString().equals(memberNum))
                dateKeys.add(rc.get("DATE_KEY").toString());
        }

        int numOfBefore = 0;

        int year = Integer.parseInt(dateKey.substring(0, 4));
        int month = Integer.parseInt(dateKey.substring(4, 6));
        int day = Integer.parseInt(dateKey.substring(6, 8));

        for (String key : dateKeys) {
            int year2 = Integer.parseInt(key.substring(0, 4));
            int month2 = Integer.parseInt(key.substring(4, 6));
            int day2 = Integer.parseInt(key.substring(6, 8));
            if (year2 < year) {
                numOfBefore++;
            } else if (year2 == year) {
                if (month2 < month) {
                    numOfBefore++;
                } else if (month2 == month) {
                    if (day2 < day) {
                        numOfBefore++;
                    }
                }
            }
        }
        return gson.toJson(numOfBefore);
    }
}
