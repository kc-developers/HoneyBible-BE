package com.kwangchun.honeybible.Service;

import com.kwangchun.honeybible.Repository.ReadcheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadcheckService {

    private final ReadcheckRepository readcheckRepository;

    public String getReadchecks() {
        return readcheckRepository.selectAll();
    }
    
    public String getReadcheck(String dateKey) {
    	return readcheckRepository.selectOne(dateKey);
    }
    
}
