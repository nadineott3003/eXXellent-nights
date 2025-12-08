package de.exxellentnights.repository;

import de.exxellentnights.entity.Room;
import de.exxellentnights.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, String> {

    @Query("""
                SELECT r
                FROM Room r
                WHERE NOT EXISTS (
                    SELECT b FROM Booking b
                    WHERE b.room = r
                    AND b.startDate < :endDate
                    AND b.endDate > :startDate
                )
                AND (:hasMinibar IS NULL OR r.hasMinibar = :hasMinibar)
                AND (:roomType IS NULL OR r.roomType = :roomType)
            """)
    List<Room> findAvailableRooms(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("hasMinibar") Boolean hasMinibar,
            @Param("roomType") RoomType roomType
    );
}
