package com.capgemini.molveno.bookingsystem.integration.services;

import com.capgemini.molveno.bookingsystem.model.BedType;
import com.capgemini.molveno.bookingsystem.model.Booking;
import com.capgemini.molveno.bookingsystem.model.Room;
import com.capgemini.molveno.bookingsystem.model.RoomType;
import com.capgemini.molveno.bookingsystem.repository.BookingRepository;
import com.capgemini.molveno.bookingsystem.repository.RoomRepository;
import com.capgemini.molveno.bookingsystem.services.BookingService;
import com.capgemini.molveno.bookingsystem.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class BookingServiceIntegrationTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    private BookingService bookingService;

    private RoomService roomService;

    @BeforeEach
    void setupTest() {
        this.bookingService = new BookingService(this.bookingRepository);
        this.roomService = new RoomService(this.roomRepository);

        this.bookingRepository.deleteAll();
        this.roomRepository.deleteAll();
        //TODO clean whole DB in order to clear ID's
    }

    @Test
    void checkInOutBooking_longId1_ChecksOutBookingAndSetsRoomsToAvailable() {
        //Arange.
        Room room_1 = this.roomService.create(new Room(RoomType.DOUBLE, 2, 5, new BedType[]{BedType.DOUBLE, BedType.DOUBLE}, true, 1, 100));
        Room room_2 = this.roomService.create(new Room(RoomType.SINGLE, 2, 5, new BedType[]{BedType.DOUBLE, BedType.DOUBLE}, true, 1, 100));
        this.bookingService.create(new Booking(Arrays.asList(room_1, room_2), "handdoeken", "01/01/2019", "01/01/2019"));

        //Act.
        List<Booking> allBookings = this.bookingService.findAll();
        Long bookingId = allBookings.get(0).getId();

        List<Room> allRooms = this.roomService.findAll();
        Long firstRoomId = allRooms.get(0).getId();
        Long secondRoomId = allRooms.get(1).getId();

        this.bookingService.checkInOutBooking(bookingId);

        //Assert.
        assertThat(allBookings.size(), is(1));
        assertThat(allRooms.size(), is(2));

        assertThat(this.bookingService.findById(bookingId).isCheckedIn(), is(true));
        assertThat(this.roomService.findById(firstRoomId).isAvailable(), is(false));
        assertThat(this.roomService.findById(secondRoomId).isAvailable(), is(false));
    }
}
