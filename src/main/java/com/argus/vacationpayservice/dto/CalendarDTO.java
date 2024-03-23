package com.argus.vacationpayservice.dto;

import com.argus.vacationpayservice.model.Month;
import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CalendarDTO {
    private Integer year;
    private List<Month> months;
}
