package com.capgemini.molveno.bookingsystem.controller;

import com.capgemini.molveno.bookingsystem.exception.DeadlineExpiredException;
import com.capgemini.molveno.bookingsystem.model.BedType;
import com.capgemini.molveno.bookingsystem.model.Booking;
import com.capgemini.molveno.bookingsystem.model.Room;
import com.capgemini.molveno.bookingsystem.model.RoomType;
import com.capgemini.molveno.bookingsystem.repository.BookingRepository;
import com.capgemini.molveno.bookingsystem.services.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    private BookingController controller;

    private Booking mockedBooking;
    private List<Booking> mockedBookingList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.controller = new BookingController(bookingService);

        this.mockedBooking = this.createMockedBooking(1l, LocalDateTime.now(), new ArrayList<>(Arrays.asList(this.createMockedRoom(1l), this.createMockedRoom(2l))));
        this.mockedBookingList = new ArrayList<>(Arrays.asList(mockedBooking));

        when(bookingService.findAll()).thenReturn(this.mockedBookingList);
        when(bookingService.findById(1L)).thenReturn(mockedBooking);
    }

    @Test
    public void getAllBookings_void_returnEmptyArrayList() {
        List<Booking> expectedResult = this.mockedBookingList;

        List<Booking> actualResult = this.controller.getAllBookings();

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    public void getBookingById_id1_returnsBooking() {
        Booking expectedBooking = this.mockedBooking;

        Booking actualBooking = this.controller.getBookingById(1L);

        assertThat(actualBooking, equalTo(expectedBooking));
    }

    @Test
    public void createBooking_inputBooking_savesNewBooking() {
        Booking inputBooking = this.mockedBooking;

        this.controller.createBooking(inputBooking);

        verify(bookingService).create(inputBooking);
    }

    @Test
    public void removeRooms_booking1room2_removesRoom2() {
        Long inputBookingId = 1L;
        Long inputRoomId = 2L;

        this.controller.removeRooms(inputBookingId, inputRoomId);

        verify(bookingService).removeRoomFromBooking(inputBookingId, inputRoomId);
    }

    @Test
    public void removeRooms_booking1room1_removesBooking() {
        Long inputBookingId = 1L;
        Long inputRoomId = 1L;
        Booking testBooking = this.createMockedBooking(inputBookingId, LocalDateTime.now(), new ArrayList<>(Arrays.asList(this.createMockedRoom(inputRoomId))));
        when(bookingService.findById(inputBookingId)).thenReturn(testBooking);

        this.controller.removeRooms(inputBookingId, inputRoomId);

        verify(bookingService).removeRoomFromBooking(inputBookingId, inputRoomId);
    }

    @Test
    public void removeRooms_bookingOlderThan1Hour_throwsDeadlineExpiredException() {
        Long inputBookingId = 1L;
        Long inputRoomId = 1L;
        BookingRepository repository = mock(BookingRepository.class);
        BookingService bookingService = new BookingService(repository);
        this.controller = new BookingController(bookingService);
        Booking testBooking = this.createMockedBooking(inputBookingId, LocalDateTime.now().minusHours(2), new ArrayList<>(Arrays.asList(this.createMockedRoom(inputRoomId))));
        when(repository.findById(inputBookingId)).thenReturn(Optional.of(testBooking));

        assertThrows(DeadlineExpiredException.class, () -> this.controller.removeRooms(inputBookingId, inputRoomId));
    }

    @Test
    public void deleteById_bookingId1_deletesBooking() {
        Long inputBookingId = 1L;

        this.controller.deleteById(inputBookingId);

        verify(this.bookingService).deleteById(inputBookingId);
    }

    @Test
    public void checkInOutBooking_longId1_togglesAvailableState() {
        Long inputBookingId = 1L;

        this.controller.checkInOutBooking(inputBookingId);

        verify(this.bookingService).checkInOutBooking(inputBookingId);
    }

    private Room createMockedRoom(Long id) {
        Room room = new Room(RoomType.SINGLE, 1, 1, new BedType[]{BedType.DOUBLE}, false, 1, 100);
        room.setId(id);

        return room;
    }

    private Booking createMockedBooking(Long bookingId, LocalDateTime bookingCreationDate, List<Room> rooms) {
        Booking booking = new Booking(rooms, "Butterflies", "01/01/2000", "01/08/2000");
        booking.setId(bookingId);
        booking.setCreationDate(bookingCreationDate);

        return booking;
    }
}
