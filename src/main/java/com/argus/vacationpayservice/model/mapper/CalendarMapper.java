package com.argus.vacationpayservice.model.mapper;

import com.argus.vacationpayservice.dto.CalendarDTO;
import com.argus.vacationpayservice.model.Calendar;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//TODO: mb use mapstruct
@Component
public class CalendarMapper {
    public Calendar convertToCalendar(CalendarDTO calendarDTO) {
        var calendar = Calendar.builder()
                .year(calendarDTO.getYear())
                .build();
        Set<LocalDate> holidays = new HashSet<>();
        calendarDTO.getMonths().forEach(month -> Arrays.stream(month.getDays().split(","))
                .forEach(s -> {
                    s = s.trim();
                    if (s.endsWith("+")) {
                        s = String.valueOf(s.charAt(0));
                    }
                    if (!s.endsWith("*")) {
                        holidays.add(LocalDate.of(calendarDTO.getYear(), month.getMonth(), Integer.parseInt(s)));
                    }
                })
        );
        calendar.setHolidays(holidays);
        return calendar;
    }
}
