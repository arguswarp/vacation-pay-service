package com.argus.vacationpayservice.controller;

import com.argus.vacationpayservice.dto.VacationPayRequest;
import com.argus.vacationpayservice.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class VacationPayController {

    private final PayService payService;
    @GetMapping("calculate")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal calculatePay(@Validated @RequestBody VacationPayRequest request){
        return null;
    }
}
