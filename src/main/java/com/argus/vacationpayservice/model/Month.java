package com.argus.vacationpayservice.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Month {

    private int month;

    private String days;
}
