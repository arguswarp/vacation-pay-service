package com.argus.vacationpayservice.model.mapper;

import com.argus.vacationpayservice.dto.CalendarDTO;
import com.argus.vacationpayservice.model.Month;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalendarMapperTest {
    private final CalendarMapper calendarMapper = new CalendarMapper();

    private final static int YEAR = 2024;

    @Test
    void WhenConvertedFromDTO_CalendarIsGiven() {
        var calendarDTO = CalendarDTO.builder()
                .year(YEAR)
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

        var calendar = calendarMapper.convertToCalendar(calendarDTO);

        assertNotNull(calendar);
        assertEquals(YEAR, calendar.getYear());
        assertTrue(calendar.getHolidays().contains(LocalDate.of(2024, 3, 8)));
    }
}