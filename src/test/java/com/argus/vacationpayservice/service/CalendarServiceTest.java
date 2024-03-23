package com.argus.vacationpayservice.service;

import com.argus.vacationpayservice.dto.CalendarDTO;
import com.argus.vacationpayservice.model.Calendar;
import com.argus.vacationpayservice.model.Month;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
        var calendar = Calendar.builder()
                .year(2024)
                .holidays(
                        Set.of(LocalDate.of(2024, 3, 8),
                                LocalDate.of(2024, 4, 8)
                        )
                )
                .build();

        var days = calendarService.excludeHolidays(
                LocalDate.of(YEAR, 3, 1),
                LocalDate.of(YEAR, 3, 10),
                calendar
        );
        assertNotNull(days);
        assertEquals(9, days);
    }

    @Test
    void convertToCalendar() {
        var calendarDTO = CalendarDTO.builder()
                .year(2024)
                .months(List.of(
                        Month.builder()
                                .month(3)
                                .days("7*, 8")
                                .build(),
                        Month.builder()
                                .month(4)
                                .days("1, 7+, 20")
                                .build()
                ))
                .build();

        var calendar = calendarService.convertToCalendar(calendarDTO);
        assertNotNull(calendar);
        assertTrue(calendar.getHolidays().contains(LocalDate.of(2024, 3, 8)));
    }
}