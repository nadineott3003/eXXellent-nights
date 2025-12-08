package de.exxellentnights.service;


import de.exxellentnights.entity.Room;
import de.exxellentnights.model.RoomType;
import de.exxellentnights.repository.BookingRepository;
import de.exxellentnights.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room getByRoomNumber(String roomNumber) {
        return roomRepository.findById(roomNumber)
                .orElseThrow(() -> new IllegalArgumentException("Room not found: " + roomNumber));
    }

    public Room create(Room room) {
        if (roomRepository.existsById(room.getRoomNumber())) {
            throw new IllegalArgumentException("Room already exists: " + room.getRoomNumber());
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

}
