package com.argus.vacationpayservice.service.impl;

import com.argus.vacationpayservice.dto.CalendarDTO;
import com.argus.vacationpayservice.service.CalendarService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CalendarServiceImpl implements CalendarService {
    @Override
    public CalendarDTO get(int year) {
        return null;
    }

    @Override
    public Integer excludeHolidays(LocalDate from, LocalDate to) {
        return null;
    }
}
