package com.capgemini.molveno.reserveringssysteem.integration.services;

import com.capgemini.molveno.reserveringssysteem.model.BedType;
import com.capgemini.molveno.reserveringssysteem.model.Room;
import com.capgemini.molveno.reserveringssysteem.model.RoomType;
import com.capgemini.molveno.reserveringssysteem.repository.BookingRepository;
import com.capgemini.molveno.reserveringssysteem.repository.RoomRepository;
import com.capgemini.molveno.reserveringssysteem.services.RoomService;
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
        Room expectedRoom = new Room(RoomType.TWO_DOUBLE, 2, 2, new BedType[]{BedType.DOUBLE, BedType.DOUBLE}, false, 1, 75);
        this.roomService.create(expectedRoom);
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
}
