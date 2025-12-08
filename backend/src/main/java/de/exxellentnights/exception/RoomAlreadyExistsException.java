package de.exxellentnights.exception;

public class RoomAlreadyExistsException extends RuntimeException {
    public RoomAlreadyExistsException(String roomNumber) {
        super("Room with number %s already exists".formatted(roomNumber));
    }
}
