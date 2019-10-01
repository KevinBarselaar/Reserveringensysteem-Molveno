package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.BedType;
import com.capgemini.molveno.reserveringssysteem.model.Room;
import com.capgemini.molveno.reserveringssysteem.model.RoomType;
import com.capgemini.molveno.reserveringssysteem.services.RoomService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;

public class RoomControllerTest {

    @Mock
    private RoomService roomService;

    private RoomController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.controller = new RoomController(roomService);

        when(roomService.findAll()).thenReturn(new ArrayList<>());
        when(roomService.findById(1L)).thenReturn(new Room(RoomType.SINGLE, 1, 1, new BedType[]{}, false, 1, 50));
    }

    @Test
    public void getAllRooms_void_returnEmptyArrayList() {
        List<Room> expectedResult = new ArrayList<>();

        List<Room> actualResult = this.controller.getAllRooms();

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    public void getRoom_id1_returnsRoom() {
        Room expectedRoom = new Room(RoomType.SINGLE, 1, 1, new BedType[]{}, false, 1, 50);

        Room actualRoom = this.controller.getRoom(1l);

        assertThat(actualRoom, equalTo(expectedRoom));
    }
}
