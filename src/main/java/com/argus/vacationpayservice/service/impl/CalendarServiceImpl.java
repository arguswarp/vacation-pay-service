package com.argus.vacationpayservice.service.impl;

import com.argus.vacationpayservice.dto.CalendarDTO;
import com.argus.vacationpayservice.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class CalendarServiceImpl implements CalendarService {

    private final RestTemplate restTemplate;

    @Override
    public CalendarDTO get(int year) {
        return restTemplate.getForObject("https://xmlcalendar.ru/data/ru/" + year + "/calendar.json", CalendarDTO.class);
    }

    @Override
    public Integer excludeHolidays(LocalDate from, LocalDate to) {
        return null;
    }
}
