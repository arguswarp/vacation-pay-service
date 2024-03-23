package com.argus.vacationpayservice.controller;

import com.argus.vacationpayservice.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
@Validated
@Slf4j
public class VacationPayController {

    private final PayService payService;

    @GetMapping("calculate")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal calculatePay(@Positive @RequestParam BigDecimal monthlyPay,
                                   @Min(0) @RequestParam(defaultValue = "0") int days,
                                   @RequestParam(required = false) LocalDate from,
                                   @RequestParam(required = false) LocalDate to) {
        if (from != null && to != null) {
            days = (int) ChronoUnit.DAYS.between(from, to) + 1;
            log.debug("Vacation duration in days " + days);
        }
        return payService.calculateByDays(monthlyPay, days);
    }
}
