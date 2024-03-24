package com.argus.vacationpayservice.service;

import com.argus.vacationpayservice.model.Calendar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CalendarServiceTest {
    private final int YEAR = 2024;
    @Autowired
    private CalendarService calendarService;

    @Test
    void WhenGetCalendarDTO_ThenCalendarIsNotNullAndWithYear() {
        var calendar = calendarService.get(YEAR);
        assertNotNull(calendar);
        assertEquals(YEAR, calendar.getYear());
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
    void WhenHolidaysExcludedFromThisAndNextYear_ThenAppropriateDaysGiven() {
        var calendarThisYear = Calendar.builder()
                .year(2024)
                .holidays(
                        Set.of(LocalDate.of(2024, 12, 31),
                                LocalDate.of(2024, 4, 8)
                        )
                )
                .build();

        var calendarNextYear = Calendar.builder()
                .year(2025)
                .holidays(
                        Set.of(LocalDate.of(2025, 1, 1),
                                LocalDate.of(2025, 2, 2)
                        )
                )
                .build();

        var days = calendarService.excludeHolidays(
                LocalDate.of(YEAR, 12, 30),
                LocalDate.of(YEAR, 1, 10),
                calendarThisYear, calendarNextYear
        );
        assertNotNull(days);
        assertEquals(12, days);
    }
}