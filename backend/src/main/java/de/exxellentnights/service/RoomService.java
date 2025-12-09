package de.exxellentnights.service;


import de.exxellentnights.entity.Room;
import de.exxellentnights.exception.InvalidDateRangeException;
import de.exxellentnights.exception.RoomAlreadyExistsException;
import de.exxellentnights.exception.RoomNotFoundException;
import de.exxellentnights.model.RoomType;
import de.exxellentnights.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return roomRepository.findAllByOrderByRoomNumberAsc();
    }

    public Room getByRoomNumber(String roomNumber) {
        return roomRepository.findById(roomNumber)
                .orElseThrow(() -> new RoomNotFoundException(roomNumber));
    }

    public Room create(Room room) {
        if (roomRepository.existsById(room.getRoomNumber())) {
            throw new RoomAlreadyExistsException(room.getRoomNumber());
        }
        return roomRepository.save(room);
    }

    public Room update(String roomNumber, RoomType roomType, boolean hasMinibar) {
        Room existing = getByRoomNumber(roomNumber);
        existing.setRoomType(roomType);
        existing.setHasMinibar(hasMinibar);
        return roomRepository.save(existing);
    }

    public void delete(String roomNumber) {
        roomRepository.deleteById(roomNumber);
    }

    public List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate, Boolean hasMinibar, RoomType roomType) {
        validateDateRange(startDate, endDate);
        return roomRepository.findAvailableRooms(startDate, endDate, hasMinibar, roomType);
    }

    private void validateDateRange(LocalDate start, LocalDate end) {
        if (start == null || end == null || !start.isBefore(end)) {
            throw new InvalidDateRangeException(start, end);
        }
    }

}
