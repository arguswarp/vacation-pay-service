package com.argus.vacationpayservice.service;

import com.argus.vacationpayservice.dto.CalendarDTO;
import com.argus.vacationpayservice.model.Calendar;

import java.time.LocalDate;

public interface CalendarService {

    CalendarDTO get(int year);

    Calendar convertToCalendar(CalendarDTO calendarDTO);

    Integer excludeHolidays(LocalDate from, LocalDate to, Calendar calendar);
}
