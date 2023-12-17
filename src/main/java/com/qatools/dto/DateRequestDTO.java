package com.qatools.dto;

import com.qatools.services.calculation.date.Date;
import lombok.Data;

@Data
public class DateRequestDTO {
    private Date dateFrom;
    private Date dateTo;
    private Boolean countLastDay;
}
