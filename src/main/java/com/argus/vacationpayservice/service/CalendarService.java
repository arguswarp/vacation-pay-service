package com.argus.vacationpayservice.service;

import com.argus.vacationpayservice.dto.CalendarDTO;

import java.time.LocalDate;

public interface CalendarService {

    CalendarDTO get(int year);

    Integer excludeHolidays(LocalDate from, LocalDate to);
}
