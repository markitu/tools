package com.qatools.controllers;

import com.qatools.dto.DateRequestDTO;
import com.qatools.services.calculation.date.Date;
import com.qatools.validators.DateValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/date")
public class DateController {

    DateValidator dateValidator = new DateValidator();

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/calculate")
    public ResponseEntity<?> calculateDifferenceBetweenTwoDates(@RequestBody DateRequestDTO dateRequestDto) {
        Date dateFrom = dateRequestDto.getDateFrom();
        Date dateTo = dateRequestDto.getDateTo();
        Boolean countLastDay = dateRequestDto.getCountLastDay();
        if (!dateValidator.isDateValid(dateFrom) || !dateValidator.isDateValid(dateTo)) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Incorrect date.");
            log.error("Received wrong date {}", dateRequestDto);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        LocalDate fromDate = LocalDate.of(Integer.parseInt(dateFrom.getYear()), Integer.parseInt(dateFrom.getMonth()), Integer.parseInt(dateFrom.getDay()));
        LocalDate toDate = LocalDate.of(Integer.parseInt(dateTo.getYear()), Integer.parseInt(dateTo.getMonth()), Integer.parseInt(dateTo.getDay()));
        long daysDifference = ChronoUnit.DAYS.between(fromDate, toDate);
        if (countLastDay) {
            daysDifference++;
        }
        return ResponseEntity.ok(daysDifference);
    }
}
