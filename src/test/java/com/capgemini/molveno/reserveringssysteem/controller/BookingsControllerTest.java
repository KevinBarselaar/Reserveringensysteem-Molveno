package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.Booking;
import com.capgemini.molveno.reserveringssysteem.model.Guest;
import com.capgemini.molveno.reserveringssysteem.model.Room;
import com.capgemini.molveno.reserveringssysteem.repository.BookingRepository;
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
import static org.mockito.Mockito.when;

public class BookingsControllerTest {

    @Mock
    private BookingRepository mockedBookingRepository;

    private BookingsController controller;

    private List<Room> mockedRooms;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.controller = new BookingsController(mockedBookingRepository);

        mockedRooms = Arrays.asList(new Room(), new Room());

        Booking mockedBooking = new Booking(mockedRooms,
                "I'd like spider webs in my rooms",
                LocalDateTime.of(1993, 1,1,1,1),
                LocalDateTime.of(1995,1,1,1,1));

        mockedBooking.setGuest(new Guest());

        when(mockedBookingRepository.findAll()).thenReturn(new ArrayList<>());
        when(mockedBookingRepository.findById(1l)).thenReturn(Optional.of(mockedBooking));

    }

    @Test
    public void getAllBookings_void_returnEmptyArrayList() {
        List<Booking> expectedResult = new ArrayList<>();

        List<Booking> actualResult = this.controller.getAllBookings();

        assertThat(actualResult, equalTo(expectedResult));
    }

    @Test
    public void getBooking_id1_returnsBooking() {
        mockedRooms = Arrays.asList(new Room(), new Room());

        Booking expectedBooking = new Booking(mockedRooms,"I'd like spider webs in my rooms",
                LocalDateTime.of(1993, 1,1,1,1),
                LocalDateTime.of(1995,1,1,1,1));

        expectedBooking.setGuest(new Guest());

        Booking actualBooking = this.controller.getBooking(1l);

        assertThat(actualBooking, equalTo(expectedBooking));
    }
}
