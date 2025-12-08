package de.exxellentnights.exception;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String roomNumber) {
        super("Room with number %s was not found".formatted(roomNumber));
    }
}
