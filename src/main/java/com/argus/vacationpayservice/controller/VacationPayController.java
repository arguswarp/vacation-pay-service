package com.argus.vacationpayservice.controller;

import com.argus.vacationpayservice.dto.PayResponse;
import com.argus.vacationpayservice.service.CalendarService;
import com.argus.vacationpayservice.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
@Validated
@Slf4j
public class VacationPayController {

    private final PayService payService;

    private final CalendarService calendarService;

    @Operation(summary = "Calculates vacation pay by period")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = PayResponse.class), mediaType = "application/json")})
    @GetMapping(value = "calculate", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public PayResponse calculatePay(@Parameter(description = "Average monthly pay") @Positive @RequestParam BigDecimal monthlyPay,
                                    @Parameter(description = "Number of days for vacation") @Min(0) @RequestParam(defaultValue = "0") int days,
                                    @Parameter(description = "Date vacation starts") @RequestParam(required = false) LocalDate from,
                                    @Parameter(description = "Date vacation ends") @RequestParam(required = false) LocalDate to) {
        log.info(String.format("Calculating request with params: " +
                "monthly pay - %.2f, day - %d, from - %s, to %s", monthlyPay, days, from, to));
        if (from != null && to != null) {
            if (from.isAfter(to)) {
                throw new DateTimeException("Date from must be before date to");
            }
            var yearFrom = from.getYear();
            var yearTo = to.getYear();
            //Выходные и праздники исключаю из продолжительности отпуска согласно ТЗ (обычно при расчете просто берется продолжительность)
            if (yearFrom == yearTo) {
                days = calendarService.excludeHolidays(from, to, calendarService.get(yearFrom));
            } else {
                days = calendarService.excludeHolidays(from, to, calendarService.get(yearFrom), calendarService.get(yearTo));
            }
            log.debug("Vacation duration in days " + days);
        }
        return new PayResponse(payService.calculateByDays(monthlyPay, days));
    }
}
