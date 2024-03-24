package com.argus.vacationpayservice.service;

import com.argus.vacationpayservice.model.Calendar;

import java.time.LocalDate;

public interface CalendarService {

    Calendar get(int year);

    Integer excludeHolidays(LocalDate from, LocalDate to, Calendar calendar);

    Integer excludeHolidays(LocalDate from, LocalDate to, Calendar calendarThisYear, Calendar calendarNextYear);
}
