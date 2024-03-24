package com.argus.vacationpayservice.service.impl;

import com.argus.vacationpayservice.exception.CalendarNotExistException;
import com.argus.vacationpayservice.model.Calendar;
import com.argus.vacationpayservice.model.mapper.CalendarMapper;
import com.argus.vacationpayservice.repository.CalendarRepository;
import com.argus.vacationpayservice.service.CalendarService;
import com.argus.vacationpayservice.service.RestClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Service
@Slf4j
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final CalendarMapper calendarMapper;
    private final RestClientService restClientService;

    @Override
    public Calendar get(int year) {
        var calendarOptional = calendarRepository.findByYear(year);
        if (calendarOptional.isPresent()) {
            log.debug("Load calendar from db");
            return calendarOptional.get();
        } else {
            try {
                var calendarDTO = restClientService.get(year);
                var calendar = calendarMapper.convertToCalendar(calendarDTO);
                log.info("Calendar for " + year + " saved to db");
                return calendarRepository.save(calendar);
            } catch (RuntimeException e) {
                throw new CalendarNotExistException("Calendar for this year not exist yet", e);
            }
        }
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
    @Override
    public Integer excludeHolidays(LocalDate from, LocalDate to, Calendar calendarThisYear, Calendar calendarNextYear) {
        var duration = ChronoUnit.DAYS.between(from, to) + 1;
        var durationResult = (int) duration;
        for (int i = 0; i < duration; i++) {
            if (calendarThisYear.getHolidays().contains(from.plusDays(i))) {
                durationResult--;
            }
            if (calendarNextYear.getHolidays().contains(from.plusDays(i))) {
                durationResult--;
            }
        }
        return durationResult;
    }
}
