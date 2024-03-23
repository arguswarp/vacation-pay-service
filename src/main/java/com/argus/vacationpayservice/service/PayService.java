package com.argus.vacationpayservice.service;

import java.math.BigDecimal;

public interface PayService {

    BigDecimal calculateByDays(BigDecimal averageMonthlyPay, int days);
}
