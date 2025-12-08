package de.exxellentnights.controller;

import de.exxellentnights.exception.RoomNotFoundException;
import de.exxellentnights.service.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class RoomsControllerTest {

    private final MockMvc mvc;

    public RoomsControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @MockitoBean
    private RoomService roomService;

    @Test
    void getRoom_shouldReturn404_whenRoomDoesNotExist() throws Exception {
        Mockito.when(roomService.getByRoomNumber("X-999")).thenThrow(new RoomNotFoundException("X-999"));
        mvc.perform(get("/rooms/X-999")).andExpect(status().isNotFound());
    }

}
