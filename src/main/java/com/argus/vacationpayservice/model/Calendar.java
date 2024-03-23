package com.argus.vacationpayservice.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Calendar {
    private int year;
    private Map<LocalDate, Boolean> holidaysMap;
}
