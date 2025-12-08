package de.exxellentnights.exception;

import java.time.LocalDate;

public class InvalidDateRangeException extends RuntimeException {
    public InvalidDateRangeException(LocalDate startDate, LocalDate endDate) {
        super("Start date (%s) must be before end date (%s).".formatted(startDate, endDate));
    }
}
