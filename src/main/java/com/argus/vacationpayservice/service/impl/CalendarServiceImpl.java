package com.argus.vacationpayservice.service.impl;

import com.argus.vacationpayservice.exception.CalendarNotExistException;
import com.argus.vacationpayservice.model.Calendar;
import com.argus.vacationpayservice.model.mapper.CalendarMapper;
import com.argus.vacationpayservice.repository.CalendarRepository;
import com.argus.vacationpayservice.service.CalendarService;
import com.argus.vacationpayservice.service.RestClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Service
@Slf4j
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    private final CalendarMapper calendarMapper;

    private final RestClientService restClientService;
    @Cacheable("calendars")
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
            } catch (RestClientException e) {
                throw new CalendarNotExistException("Calendar for this year not exist", e);
            }
        }
    }

    @Override
    public Integer excludeHolidays(LocalDate from, LocalDate to, Calendar calendar) {
        var duration = (int) ChronoUnit.DAYS.between(from, to) + 1;
        var holidays = countHolidays(duration, i -> calendar.getHolidays().contains(from.plusDays(i)));
        return duration - holidays;
    }

    @Override
    public Integer excludeHolidays(LocalDate from, LocalDate to, Calendar calendarThisYear, Calendar calendarNextYear) {
        var duration = (int) ChronoUnit.DAYS.between(from, to) + 1;
        var holidaysThisYear = countHolidays(duration, i -> calendarThisYear.getHolidays().contains(from.plusDays(i)));
        var holidaysNextYear = countHolidays(duration, i -> calendarNextYear.getHolidays().contains(from.plusDays(i)));
        return duration - holidaysThisYear - holidaysNextYear;
    }

    private Integer countHolidays(int duration, Predicate<Integer> predicate) {
        var holidays = 0;
        for (int i = 0; i < duration; i++) {
            if (predicate.test(i)) {
                holidays++;
            }
        }
        return holidays;
    }
}
