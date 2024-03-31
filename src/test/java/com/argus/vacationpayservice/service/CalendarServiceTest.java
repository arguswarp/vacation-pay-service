package com.argus.vacationpayservice.service;

import com.argus.vacationpayservice.exception.CalendarNotExistException;
import com.argus.vacationpayservice.model.Calendar;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class CalendarServiceTest {

    private final int YEAR = 2024;

    @Autowired
    private CalendarService calendarService;

    @Test
    @Transactional
    void WhenGetCalendar_ThenCalendarIsNotNullAndWithYear() {
        var calendar = calendarService.get(YEAR);
        assertNotNull(calendar);
        assertEquals(YEAR, calendar.getYear());
    }

    @Test
    @Transactional
    void WhenHolidaysExcluded_ThenAppropriateDaysGiven() {
        var calendar = Calendar.builder()
                .year(YEAR)
                .holidays(
                        Set.of(LocalDate.of(YEAR, 3, 8),
                                LocalDate.of(YEAR, 4, 8)
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
    @Transactional
    void WhenHolidaysExcludedFromThisAndNextYear_ThenAppropriateDaysGiven() {
        var calendarThisYear = Calendar.builder()
                .year(2024)
                .holidays(
                        Set.of(LocalDate.of(YEAR, 12, 31),
                                LocalDate.of(YEAR, 4, 8)
                        )
                )
                .build();

        var calendarNextYear = Calendar.builder()
                .year(2025)
                .holidays(
                        Set.of(LocalDate.of(YEAR + 1, 1, 1),
                                LocalDate.of(YEAR + 1, 2, 2)
                        )
                )
                .build();

        var days = calendarService.excludeHolidays(
                LocalDate.of(YEAR, 12, 30),
                LocalDate.of(YEAR + 1, 1, 10),
                calendarThisYear, calendarNextYear
        );
        assertNotNull(days);
        assertEquals(10, days);
    }

    @Test
    @Transactional
    void WhenGetCalendarNotExisting_CalendarNotExistExceptionThrown() {
        assertThrows(CalendarNotExistException.class, () -> calendarService.get(LocalDate.now().getYear() + 2));
    }

    @Test
    @Transactional
    void WhenGetMultipleTimes_ThenGetFromCache() {
        calendarService.get(LocalDate.now().getYear());
        log.info("calendar found: {}", calendarService.get(YEAR));
        log.info("calendar found: {}", calendarService.get(YEAR));
        log.info("calendar found: {}", calendarService.get(YEAR));
    }
}