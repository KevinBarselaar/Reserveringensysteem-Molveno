package com.capgemini.molveno.bookingsystem.controller;

import com.capgemini.molveno.bookingsystem.model.BedType;
import com.capgemini.molveno.bookingsystem.model.Room;
import com.capgemini.molveno.bookingsystem.model.RoomType;
import com.capgemini.molveno.bookingsystem.services.RoomService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.verify;
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
        when(roomService.findAllAvailable()).thenReturn(Arrays.asList(new Room(RoomType.SINGLE, 1, 1, new BedType[]{}, false, 1, 50)));
    }

    @Test
    public void getAllRooms_void_returnEmptyArrayList() {
        List<Room> expectedResult = new ArrayList<>();

        List<Room> actualResult = this.controller.getAllRooms();

        assertThat(actualResult, equalTo(expectedResult));
        verify(this.roomService).findAll();
    }

    @Test
    public void getRoomById_id1_returnsRoom() {
        Room expectedRoom = new Room(RoomType.SINGLE, 1, 1, new BedType[]{}, false, 1, 50);

        Room actualRoom = this.controller.getRoomById(1L);

        assertThat(actualRoom, equalTo(expectedRoom));
        verify(this.roomService).findById(1L);
    }

    @Test
    public void getAllAvailableRooms_void_returnsListOfSize1() {
        int expectedListSize = 1;

        int actualListSize = this.controller.getAllAvailableRooms().size();

        assertThat(actualListSize, is(expectedListSize));
        verify(this.roomService).findAllAvailable();
    }

    @Test
    public void create_inputRoom_savesNewRoom() {
        Room inputRoom = new Room(RoomType.SINGLE, 1, 1, new BedType[]{}, false, 1, 50);

        this.controller.create(inputRoom);

        verify(this.roomService).create(inputRoom);
    }

    @Test
    public void deleteById_inputRoomId1_deletesRoomWithId1() {
        Long inputRoomId = 1L;

        this.controller.deleteById(inputRoomId);

        verify(this.roomService).deleteById(inputRoomId);
    }
}
