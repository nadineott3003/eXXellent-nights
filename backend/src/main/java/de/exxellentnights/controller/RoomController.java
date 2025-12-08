package de.exxellentnights.controller;

import de.exxellentnights.api.RoomsApi;
import de.exxellentnights.api.model.RoomCreateDto;
import de.exxellentnights.api.model.RoomDto;
import de.exxellentnights.api.model.RoomTypeDto;
import de.exxellentnights.api.model.RoomUpdateDto;
import de.exxellentnights.entity.Room;
import de.exxellentnights.model.RoomType;
import de.exxellentnights.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class RoomController implements RoomsApi {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public ResponseEntity<RoomDto> createRoom(RoomCreateDto roomCreateDto) {
        Room room = new Room();
        room.setRoomNumber(roomCreateDto.getRoomNumber());
        room.setRoomType(RoomType.valueOf(roomCreateDto.getRoomType().name()));
        room.setHasMinibar(Boolean.TRUE.equals(roomCreateDto.getHasMinibar()));
        return ResponseEntity.ok(toDto(roomService.create(room)));
    }

    @Override
    public ResponseEntity<String> deleteRoom(String roomNumber) {
        roomService.delete(roomNumber);
        return ResponseEntity.ok("Zimmer %s gelöscht.".formatted(roomNumber));
    }

    @Override
    public ResponseEntity<RoomDto> getRoom(String roomNumber) {
        RoomDto roomDto = toDto(roomService.getByRoomNumber(roomNumber));
        return ResponseEntity.ok(roomDto);
    }

    @Override
    public ResponseEntity<List<RoomDto>> getRooms() {
        List<RoomDto> roomDtoList = roomService.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(roomDtoList);
    }

    @Override
    public ResponseEntity<RoomDto> updateRoom(String roomNumber, RoomUpdateDto roomUpdateDto) {
        RoomType type = RoomType.valueOf(roomUpdateDto.getRoomType().name());
        boolean minibar = Boolean.TRUE.equals(roomUpdateDto.getHasMinibar());
        return ResponseEntity.ok(toDto(roomService.update(roomNumber, type, minibar)));
    }

    @Override
    public ResponseEntity<List<RoomDto>> getAvailableRooms(LocalDate startDate, LocalDate endDate, Boolean hasMinibar, RoomTypeDto roomType) {
        return null;
    }

    private RoomDto toDto(Room room) {
        RoomDto dto = new RoomDto();
        dto.setRoomNumber(room.getRoomNumber());
        dto.setRoomType(RoomTypeDto.valueOf(room.getRoomType().name()));
        dto.setHasMinibar(room.hasMinibar());
        return dto;
    }
}
