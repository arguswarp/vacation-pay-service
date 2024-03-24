package com.argus.vacationpayservice.service;

import com.argus.vacationpayservice.dto.CalendarDTO;

public interface RestClientService {
    CalendarDTO get (int year);
}
