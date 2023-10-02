package com.kwangchun.honeybible.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.kwangchun.honeybible.Repository.CalendarRepository;
import com.kwangchun.honeybible.Repository.UserRepository;
import com.kwangchun.honeybible.dto.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import java.util.Date;

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
