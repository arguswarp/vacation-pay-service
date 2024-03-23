package com.argus.vacationpayservice.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Calendar {
    private int year;
    private Set<LocalDate> holidays;
}
