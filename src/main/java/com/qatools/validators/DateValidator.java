package com.qatools.validators;

import com.qatools.services.calculation.date.Date;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Month;

@Slf4j
public class DateValidator {

    public boolean isDateValid(Date dateData) {
        if (!isNumeric(dateData.getDay()) || !isNumeric(dateData.getYear()) || !isNumeric(dateData.getMonth())) {
            return false;
        }
        int year = Integer.parseInt(dateData.getYear());
        int month = Integer.parseInt(dateData.getMonth());
        int day = Integer.parseInt(dateData.getDay());

        if (year <= 0 || month <= 0 || day <= 0) {
            return false;
        }
        if (month > 12) {
            return false;
        }
        Month enumMonth = Month.of(month);
        int daysInMonth = enumMonth.length(LocalDate.of(year, month, 1).isLeapYear());
        return day <= daysInMonth;
    }

    private boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            log.error("Input date: {} is not numeric", input);
            return false;
        }
    }


}
