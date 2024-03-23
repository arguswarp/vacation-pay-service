package com.argus.vacationpayservice.service;

import com.argus.vacationpayservice.service.impl.PayServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PayServiceTest {

    private final PayService payService = new PayServiceImpl();

    @Test
    void WhenCalculateByDays_PayAmountIsGiven() {
        var days = 12;
        var monthlyPay = BigDecimal.valueOf(100_000);
        var vacationPayExpected = BigDecimal.valueOf(35631.41);

        var amount = payService.calculateByDays(monthlyPay, days);

        assertNotNull(amount);
        assertEquals(vacationPayExpected, amount);
    }
}