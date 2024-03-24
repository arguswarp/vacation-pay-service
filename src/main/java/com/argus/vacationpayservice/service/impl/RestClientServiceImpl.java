package com.argus.vacationpayservice.service.impl;

import com.argus.vacationpayservice.dto.CalendarDTO;
import com.argus.vacationpayservice.service.RestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class RestClientServiceImpl implements RestClientService {

    private final String URL_BEGIN = "https://xmlcalendar.ru/data/ru/";

    private final String URL_END = "/calendar.json";

    private final RestTemplate restTemplate;

    @Override
    public CalendarDTO get(int year) {
        return restTemplate.getForObject(URL_BEGIN + year + URL_END, CalendarDTO.class);
    }
}
