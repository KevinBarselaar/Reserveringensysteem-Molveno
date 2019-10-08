package com.capgemini.molveno.bookingsystem.integration.services;

import com.capgemini.molveno.bookingsystem.model.*;
import com.capgemini.molveno.bookingsystem.repository.BookingRepository;
import com.capgemini.molveno.bookingsystem.repository.RoomRepository;
import com.capgemini.molveno.bookingsystem.services.BookingService;
import com.capgemini.molveno.bookingsystem.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

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
    }

    @Test
    void checkInOutBooking_longId1_ChecksOutBookingAndSetsRoomsToAvailable() {
        //Arange.
        Room room_1 = this.roomService.create(new Room(RoomType.DOUBLE, 2, 5, new BedType[]{BedType.DOUBLE, BedType.DOUBLE}, true, 1, 100));
        Room room_2 = this.roomService.create(new Room(RoomType.SINGLE, 2, 5, new BedType[]{BedType.DOUBLE, BedType.DOUBLE}, true, 1, 100));
        this.bookingService.create(new Booking(Arrays.asList(room_1, room_2), "handdoeken", new Date(), new Date()));

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

    //TODO booking start/einddatum zijn nu strings -> naar Date, anders kan je niet querieen.
    //TODO Room krijgt een bidirectionele relatie met booking: booking heeft n rooms, room heeft 1 booking
    //TODO select room r from Room where r.booking.startdatum and r.booking.enddatum NOT between :startDatum AND :einddatum
    //TODO voeg de AND room.available = true; toe?
    //TODO wat als je een kamer boekt op een 'beschikbare' datum?

    @Test
    void findAllAvailableBetweenDates_inputStartAndEndDate_returnsListOf2Rooms() {
        Room firstRoom = new Room(RoomType.SINGLE, 1, 0, new BedType[]{BedType.SINGLE}, false, 0, 95);
        Room secondRoom = new Room(RoomType.DOUBLE, 2, 0, new BedType[]{BedType.DOUBLE}, true, 0, 180);
        Address guestAddress = new Address("Street name", 1, "A", "1234AB", "Village", "Netherlands");
        MainGuest guest = new MainGuest(Title.MR, "James", "Bond", new Date(), "0123456789", "maybe@yes.no", guestAddress);
        Date inputStartDate = this.createDate(8, 10, 2019);
        Date inputEndDate = this.createDate(12, 10, 2019);
        Booking inputBooking = new Booking(Arrays.asList(firstRoom, secondRoom), "", inputStartDate, inputEndDate, guest, new ArrayList<>());
        this.bookingService.create(inputBooking);
        Room firstAvailableRoom = this.roomService.create(new Room(RoomType.PENTHOUSE, 2, 0, new BedType[]{BedType.DOUBLE}, true, 0, 180));
        Room secondAvailableRoom = this.roomService.create(new Room(RoomType.TWO_DOUBLE, 2, 0, new BedType[]{BedType.DOUBLE}, true, 0, 180));
        List<Room> expectedAvailableRooms = Arrays.asList(firstAvailableRoom, secondAvailableRoom);

        List<Room> actualAvailableRooms = this.roomService.findAllAvailableBetweenDates(inputStartDate, inputEndDate);

        assertThat(actualAvailableRooms.toString(), equalTo(expectedAvailableRooms.toString()));
    }

    private Date createDate(int day, int month, int year) {
        return new GregorianCalendar(year, month, day).getTime();
    }
}
