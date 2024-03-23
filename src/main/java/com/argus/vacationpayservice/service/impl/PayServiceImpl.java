package com.argus.vacationpayservice.service.impl;

import com.argus.vacationpayservice.service.PayService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
@Service
public class PayServiceImpl implements PayService {

    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;

    private static final double NDFL = 0.13;

    private final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

    @Override
    public BigDecimal calculateByDays(BigDecimal averageMonthlyPay, int days) {
        var workDays = AVERAGE_DAYS_IN_MONTH * 12;
        var dayIncome = averageMonthlyPay
                .multiply(BigDecimal.valueOf(12))
                .divide(BigDecimal.valueOf(workDays), 2, ROUNDING_MODE);
        return dayIncome.multiply(BigDecimal.valueOf(days)).multiply(BigDecimal.valueOf(1 - NDFL)).setScale(2, ROUNDING_MODE);
    }
}
