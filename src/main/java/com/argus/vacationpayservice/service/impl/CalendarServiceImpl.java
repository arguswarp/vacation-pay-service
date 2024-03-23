package com.argus.vacationpayservice.service.impl;

import com.argus.vacationpayservice.dto.CalendarDTO;
import com.argus.vacationpayservice.model.Calendar;
import com.argus.vacationpayservice.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CalendarServiceImpl implements CalendarService {

    private final RestTemplate restTemplate;

    @Override
    public CalendarDTO get(int year) {
        return restTemplate.getForObject("https://xmlcalendar.ru/data/ru/" + year + "/calendar.json", CalendarDTO.class);
    }

    @Override
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

    @Override
    public Integer excludeHolidays(LocalDate from, LocalDate to, Calendar calendar) {
        var duration = ChronoUnit.DAYS.between(from, to) + 1;
        var durationResult = (int) duration;
        for (int i = 0; i < duration; i++) {
            if (calendar.getHolidays().contains(from.plusDays(i))) {
                durationResult--;
            }
        }
        return durationResult;
    }
}
