package com.capgemini.molveno.bookingsystem.integration.services;

import com.capgemini.molveno.bookingsystem.model.BedType;
import com.capgemini.molveno.bookingsystem.model.Room;
import com.capgemini.molveno.bookingsystem.model.RoomType;
import com.capgemini.molveno.bookingsystem.repository.BookingRepository;
import com.capgemini.molveno.bookingsystem.repository.RoomRepository;
import com.capgemini.molveno.bookingsystem.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    private RoomService roomService;

    @BeforeEach
    void setupTest() {
        this.roomService = new RoomService(this.roomRepository);

        this.bookingRepository.deleteAll();
        this.roomRepository.deleteAll();
    }

    @Test
    void findAll_void_returnsListOfRooms() {
        int expectedListSize = 2;
        List<Room> mockedRooms = List.of(
                new Room(RoomType.TWO_DOUBLE, 2, 2, new BedType[]{BedType.DOUBLE, BedType.DOUBLE}, false, 1, 75),
                new Room(RoomType.PENTHOUSE, 1, 2, new BedType[]{BedType.DOUBLE, BedType.DOUBLE, BedType.SINGLE}, true, 1, 250));
        this.roomRepository.saveAll(mockedRooms);

        List<Room> allRooms = this.roomService.findAll();
        int actualListSize = allRooms.size();

        assertThat(actualListSize, equalTo(expectedListSize));
    }

    @Test
    void findById_longRoomId_returnsRoom() {
        Room expectedRoom = this.roomService.create(new Room(RoomType.TWO_DOUBLE, 2, 2, new BedType[]{BedType.DOUBLE, BedType.DOUBLE}, false, 1, 75));
        List<Room> allRooms = this.roomService.findAll();
        Long roomId = allRooms.get(0).getId();

        Room actualRoom = this.roomService.findById(roomId);

        assertThat(allRooms.size(), is(1));
        assertThat(actualRoom, equalTo(expectedRoom));
    }

    @Test
    void findAllAvailable_newAvailableRoom_returnsAvailableRoom() {
        Room expectedAvailableRoom = new Room(RoomType.PENTHOUSE, 1, 2, new BedType[]{BedType.DOUBLE, BedType.DOUBLE, BedType.SINGLE}, true, 1, 250);
        expectedAvailableRoom.setAvailable(true);
        this.roomService.create(expectedAvailableRoom);

        Room actualAvailableRoom = this.roomService.findAllAvailable().get(0);

        assertThat(actualAvailableRoom, is(expectedAvailableRoom));
    }

    @Test
    void create_newRoom_returnsRoom() {
        Room expectedRoom = new Room(RoomType.SINGLE, 1, 0, new BedType[]{BedType.SINGLE}, false, 2, 50);

        this.roomService.create(expectedRoom);
        Room actualRoom = this.roomService.findAll().get(0);

        assertThat(actualRoom, is(expectedRoom));
    }

    @Test
    void deleteById_roomWithId_deletesRoom() {
        Room expectedRoomToDelete = this.roomService.create(new Room(RoomType.PENTHOUSE, 1, 2, new BedType[]{BedType.DOUBLE}, true, 1, 250));

        this.roomService.deleteById(expectedRoomToDelete.getId());
        List<Room> allRooms = this.roomService.findAll();

        assertThat(allRooms.size(), is(0));
    }
}
