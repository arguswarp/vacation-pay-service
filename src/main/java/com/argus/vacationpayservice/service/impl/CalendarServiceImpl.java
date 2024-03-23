package com.argus.vacationpayservice.service.impl;

import com.argus.vacationpayservice.dto.CalendarDTO;
import com.argus.vacationpayservice.service.CalendarService;

import java.time.LocalDate;

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
