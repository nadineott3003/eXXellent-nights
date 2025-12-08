package de.exxellentnights.mapper;

import de.exxellentnights.api.model.RoomDto;
import de.exxellentnights.entity.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomDto toDto(Room room);

    Room toEntity(RoomDto dto);
}
