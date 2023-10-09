package com.kwangchun.honeybible.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.kwangchun.honeybible.Repository.CalendarRepository;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public String getCalendars() {
        return calendarRepository.selectAll();
    }
    
    public String getCalendar(String dateKey) {
    	return calendarRepository.selectOne(dateKey);
    }
    
}
