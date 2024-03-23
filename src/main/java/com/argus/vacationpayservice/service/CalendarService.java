package com.argus.vacationpayservice.service;

import com.argus.vacationpayservice.dto.CalendarDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface CalendarService {

    CalendarDTO get(int year);

    Integer excludeHolidays(LocalDate from, LocalDate to);
}
