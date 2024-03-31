package com.argus.vacationpayservice.model.mapper;

import com.argus.vacationpayservice.dto.CalendarDTO;
import com.argus.vacationpayservice.model.Calendar;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CalendarMapper {
    @Mapping(target = "year", source = "year")
    @Mapping(target = "holidays", ignore = true)
    @Mapping(target = "id", ignore = true)
    Calendar calendarDTOToCalendar(CalendarDTO calendarDTO);

    @AfterMapping
    default void monthsListToHolidaysSet(@MappingTarget Calendar.CalendarBuilder builder, CalendarDTO calendarDTO) {
        Set<LocalDate> holidays = new HashSet<>();
        calendarDTO.getMonths().forEach(month -> Arrays.stream(month.getDays().split(","))
                .forEach(s -> {
                    s = s.trim();
                    if (s.endsWith("+")) {
                        s = String.valueOf(s.charAt(0));
                    }
                    if (!s.endsWith("*")) {
                        holidays.add(LocalDate.of(LocalDate.now().getYear(), month.getMonth(), Integer.parseInt(s)));
                    }
                })
        );
        builder.holidays(holidays);
    }
}
