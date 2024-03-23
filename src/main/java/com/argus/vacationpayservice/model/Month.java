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
public class Month {
    private int month;

    private List<Integer> days;
}
