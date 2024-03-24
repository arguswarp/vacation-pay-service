package com.argus.vacationpayservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class PayResponse {
    @JsonProperty("value")
    private BigDecimal value;
}
