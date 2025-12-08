package de.exxellentnights.repository;

import de.exxellentnights.entity.Room;
import de.exxellentnights.model.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class RoomRepositoryTest {

    private final RoomRepository repository;

    private final TestEntityManager entityManager;

    public RoomRepositoryTest(RoomRepository repository, TestEntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Test
    void findAvailableRooms_shouldReturnRoom() {
        Room room = new Room();
        room.setRoomNumber("400");
        room.setRoomType(RoomType.DOUBLE);
        room.setHasMinibar(true);
        entityManager.persist(room);

        // Buchung (1.–5. Dezember)
        entityManager.getEntityManager().createNativeQuery("""
                    INSERT INTO booking (room_number, start_date, end_date)
                    VALUES ('400', '2025-12-01', '2025-12-05')
                """).executeUpdate();

        // Buchung (10.–15. Dezember)
        entityManager.getEntityManager().createNativeQuery("""
                    INSERT INTO booking (room_number, start_date, end_date)
                    VALUES ('400', '2025-12-10', '2025-12-15')
                """).executeUpdate();

        // Zeitraum 05.–10. Dezember (frei)
        List<Room> result = repository.findAvailableRooms(
                LocalDate.of(2025, 12, 5),
                LocalDate.of(2025, 12, 10),
                null,
                null
        );

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getRoomNumber()).isEqualTo("400");
    }

    @Test
    void findAvailableRooms_shouldReturnEmpty() {
        Room room = new Room();
        room.setRoomNumber("200");
        room.setRoomType(RoomType.DOUBLE);
        room.setHasMinibar(true);
        entityManager.persist(room);

        // Buchung (1.–5. Dezember)
        entityManager.getEntityManager().createNativeQuery("""
                    INSERT INTO booking (room_number, start_date, end_date)
                    VALUES ('200', '2025-12-01', '2025-12-05')
                """).executeUpdate();

        // Zeitraum 03.–10. Dezember (belegt)
        List<Room> result = repository.findAvailableRooms(
                LocalDate.of(2025, 12, 3),
                LocalDate.of(2025, 12, 10),
                null,
                null
        );

        assertThat(result).isEmpty();
    }


}
