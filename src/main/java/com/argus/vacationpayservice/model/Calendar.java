package com.argus.vacationpayservice.model;

import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Calendar {
    private int year;
    private List<Month> months;
}
