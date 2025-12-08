package de.exxellentnights.exception;

import de.exxellentnights.api.model.ExxResponseObj;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ExxResponseObj> handleRoomNotFound(RoomNotFoundException ex) {
        ExxResponseObj body = new ExxResponseObj(HttpStatus.NOT_FOUND.value(), "ROOM_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(RoomAlreadyExistsException.class)
    public ResponseEntity<ExxResponseObj> handleRoomAlreadyExists(RoomAlreadyExistsException ex) {
        ExxResponseObj body = new ExxResponseObj(HttpStatus.CONFLICT.value(), "ROOM_ALREADY_EXISTS", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(InvalidDateRangeException.class)
    public ResponseEntity<ExxResponseObj> handleInvalidDateRangeException(InvalidDateRangeException ex) {
        ExxResponseObj body = new ExxResponseObj(HttpStatus.BAD_REQUEST.value(), "INVALID_DATE_RANGE", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // fallback: Catch-all fall für sonstige Fehler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExxResponseObj> handleGeneric(Exception ex) {
        ExxResponseObj body = new ExxResponseObj(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
