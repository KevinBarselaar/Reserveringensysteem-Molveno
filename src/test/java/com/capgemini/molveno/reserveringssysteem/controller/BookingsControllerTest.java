package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.*;
import com.capgemini.molveno.reserveringssysteem.repository.BookingRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookingsControllerTest {

    @Mock
    private BookingRepository mockedBookingRepository;

    private BookingsController controller;

    private Booking mockedBooking;
    private List<Booking> mockedBookingList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.controller = new BookingsController(mockedBookingRepository);

        this.mockedBooking = this.createMockedBooking(1l, LocalDateTime.now());
        this.mockedBookingList = new ArrayList<>(Arrays.asList(mockedBooking));

        when(mockedBookingRepository.findAll()).thenReturn(this.mockedBookingList);
        when(mockedBookingRepository.findById(1l)).thenReturn(Optional.of(mockedBooking));
    }

    @Test
    public void getAllBookings_void_returnEmptyArrayList() {
        List<Booking> expectedResult = this.mockedBookingList;

        List<Booking> actualResult = this.controller.getAllBookings();

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    public void getBooking_id1_returnsBooking() {
        Booking expectedBooking = this.mockedBooking;

        Booking actualBooking = this.controller.getBooking(1l);

        assertThat(actualBooking, equalTo(expectedBooking));
    }

    @Test
    public void removeRooms_booking1room2_removesRoom2() {
        Long inputBookingId = 1l;
        Long inputRoomId = 2l;

        this.controller.removeRooms(inputBookingId, inputRoomId);

        verify(mockedBookingRepository).save(mockedBooking);
    }

    private Booking createMockedBooking(Long bookingId, LocalDateTime bookingCreationDate) {
        Room room1 = new Room(RoomType.SINGLE, 1, 1, new BedType[]{BedType.DOUBLE}, false, 1, 100);
        room1.setId(1l);

        Room room2 = new Room(RoomType.DOUBLE, 2, 1, new BedType[]{BedType.DOUBLE, BedType.BABY}, true, 2, 200);
        room1.setId(2l);

        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);

        Booking booking = new Booking(rooms, "Butterflies", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusWeeks(1));
        booking.setId(bookingId);
        booking.setCreationDate(bookingCreationDate);

        return booking;
    }
}
