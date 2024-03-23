package com.argus.vacationpayservice.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public interface PayService {

    BigDecimal calculateByDays(BigDecimal averageMonthlyPay, int days);
}
