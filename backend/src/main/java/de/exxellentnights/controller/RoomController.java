package de.exxellentnights.controller;

import de.exxellentnights.api.RoomsApi;
import de.exxellentnights.api.model.RoomCreateDto;
import de.exxellentnights.api.model.RoomDto;
import de.exxellentnights.api.model.RoomTypeDto;
import de.exxellentnights.api.model.RoomUpdateDto;
import de.exxellentnights.entity.Room;
import de.exxellentnights.mapper.RoomMapper;
import de.exxellentnights.model.RoomType;
import de.exxellentnights.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class RoomController implements RoomsApi {

    private final RoomMapper roomMapper;

    private final RoomService roomService;

    public RoomController(RoomMapper roomMapper, RoomService roomService) {
        this.roomMapper = roomMapper;
        this.roomService = roomService;
    }

    @Override
    public ResponseEntity<RoomDto> createRoom(RoomCreateDto roomCreateDto) {
        Room room = new Room();
        room.setRoomNumber(roomCreateDto.getRoomNumber());
        room.setRoomType(RoomType.valueOf(roomCreateDto.getRoomType().name()));
        room.setHasMinibar(Boolean.TRUE.equals(roomCreateDto.getHasMinibar()));
        return ResponseEntity.ok(roomMapper.toDto(roomService.create(room)));
    }

    @Override
    public ResponseEntity<String> deleteRoom(String roomNumber) {
        roomService.delete(roomNumber);
        return ResponseEntity.ok("Zimmer %s gelöscht.".formatted(roomNumber));
    }

    @Override
    public ResponseEntity<RoomDto> getRoom(String roomNumber) {
        RoomDto roomDto = roomMapper.toDto(roomService.getByRoomNumber(roomNumber));
        return ResponseEntity.ok(roomDto);
    }

    @Override
    public ResponseEntity<List<RoomDto>> getRooms() {
        List<RoomDto> roomDtoList = roomService.findAll().stream().map(roomMapper::toDto).toList();
        return ResponseEntity.ok(roomDtoList);
    }

    @Override
    public ResponseEntity<RoomDto> updateRoom(String roomNumber, RoomUpdateDto roomUpdateDto) {
        RoomType type = RoomType.valueOf(roomUpdateDto.getRoomType().name());
        boolean minibar = Boolean.TRUE.equals(roomUpdateDto.getHasMinibar());
        return ResponseEntity.ok(roomMapper.toDto(roomService.update(roomNumber, type, minibar)));
    }

    @Override
    public ResponseEntity<List<RoomDto>> getAvailableRooms(LocalDate startDate, LocalDate endDate, Boolean hasMinibar, RoomTypeDto roomTypeDto) {
        RoomType roomType = Optional.ofNullable(roomTypeDto)
                .map(Enum::name)
                .map(RoomType::valueOf)
                .orElse(null);

        List<RoomDto> availableRoomDtoList = roomService.findAvailableRooms(startDate, endDate, hasMinibar, roomType)
                .stream().map(roomMapper::toDto).toList();
        return ResponseEntity.ok(availableRoomDtoList);
    }

}
