package de.exxellentnights.service;

import de.exxellentnights.exception.InvalidDateRangeException;
import de.exxellentnights.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RoomServiceTest {

    @Test
    void testInvalidDateRange_datesAreNull() {
        RoomService service = new RoomService(Mockito.mock(RoomRepository.class));
        assertThrows(InvalidDateRangeException.class, () ->
                service.findAvailableRooms(null, null, null, null)
        );
    }

    @Test
    void testInvalidDateRange_endDateBeforeStartDate() {
        RoomService service = new RoomService(Mockito.mock(RoomRepository.class));
        assertThrows(InvalidDateRangeException.class, () -> service.findAvailableRooms(
                        LocalDate.of(2025, 12, 2),
                        LocalDate.of(2025, 12, 1),
                        null,
                        null
                )
        );
    }
}
