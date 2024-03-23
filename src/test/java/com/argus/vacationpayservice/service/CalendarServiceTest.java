package com.argus.vacationpayservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CalendarServiceTest {

    private final int YEAR = 2024;

    @Autowired
    private CalendarService calendarService;

    @Test
    void WhenGetCalendarDTO_ThenCalendarIsNotNullAndWithYear() {
        var calendarDTO = calendarService.get(YEAR);
        assertNotNull(calendarDTO);
        assertEquals(YEAR, calendarDTO.getYear());
    }

    @Test
    void WhenHolidaysExcluded_ThenAppropriateDaysGiven() {
        var days = calendarService.excludeHolidays(
                LocalDate.of(YEAR, 3, 1),
                LocalDate.of(YEAR, 3, 10)
        );
        assertNotNull(days);
        assertEquals(9, days);
    }
}