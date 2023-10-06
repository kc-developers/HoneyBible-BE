package com.kwangchun.honeybible.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kwangchun.honeybible.Service.CalendarService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {
    private final Logger logger = LoggerFactory.getLogger(CalendarController.class);
    
    private final CalendarService calendarService;

    @GetMapping("/all")
    public String getCalendars() {
        logger.info("[GET] all calendars");

        String calendars = calendarService.getCalendars();
        logger.info("- all calendars : {}", calendars);

        return calendars;
    }
    
    @GetMapping("/{dateKey}")
    public String getCalendar(@PathVariable String dateKey) {
        logger.info("[GET] calendar");

        String calendar = calendarService.getCalendar(dateKey);
        logger.info("- calendar info : {}", calendar);

        return calendar;
    }

}
