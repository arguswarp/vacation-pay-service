package com.argus.vacationpayservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VacationPayRequest {
    //TODO: validations
    BigDecimal monthlyPay;
    Integer days;
    LocalDate from;
    LocalDate to;
}
