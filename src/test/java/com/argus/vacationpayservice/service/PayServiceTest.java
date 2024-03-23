package com.argus.vacationpayservice.service;

import com.argus.vacationpayservice.service.impl.PayServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class PayServiceTest {

    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;

    private final PayService payService = new PayServiceImpl();

    private final MathContext mathContext = new MathContext(2, RoundingMode.HALF_EVEN);

    @Test
    void WhenCalculateByDays_PayAmountIsGiven() {
        var days = 10;
        var monthlyPay = BigDecimal.valueOf(100_000);
        var workDays = AVERAGE_DAYS_IN_MONTH * 12;
        var dayIncome = monthlyPay
                .multiply(BigDecimal.valueOf(12))
                .divide(BigDecimal.valueOf(workDays), mathContext);
        var vacationPayExpected = dayIncome.multiply(BigDecimal.valueOf(days), mathContext);

        var amount = payService.calculateByDays(monthlyPay, days);

        assertNotNull(amount);
        assertEquals(vacationPayExpected, amount);
    }
}