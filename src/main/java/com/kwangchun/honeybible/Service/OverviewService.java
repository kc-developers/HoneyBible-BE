package com.kwangchun.honeybible.Service;

import com.kwangchun.honeybible.Repository.OverviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OverviewService {

    private final OverviewRepository overviewRepository;

    public String getOverviews() {
        return overviewRepository.selectAll();
    }
    
    public String getOverview(String dateKey) {
    	return overviewRepository.selectOne(dateKey);
    }
    
}
