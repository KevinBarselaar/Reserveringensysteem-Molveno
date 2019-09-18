package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.Booking;
import com.capgemini.molveno.reserveringssysteem.model.Room;
import com.capgemini.molveno.reserveringssysteem.repository.BookingRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;

public class BookingsControllerTest {

    @Mock   //'nep' repository waarmee je de repository kan na simuleren
    private BookingRepository mockedBookingRepository;

    private BookingsController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this); //Mockito maakt alle mocks aan van deze klasse

        this.controller = new BookingsController(mockedBookingRepository);

        List<Room> mockedRooms = Arrays.asList(new Room(), new Room());

        when(mockedBookingRepository.findAll()).thenReturn(new ArrayList<>()); //gedrag specificeren
        when(mockedBookingRepository.findById(1l)).thenReturn(Optional.of(new Booking(mockedRooms, "I'd like spider webs in my rooms", LocalDateTime.of(1993, 1,1,1,1), LocalDateTime.of(1995,1,1,1,1)))); //De repo wordt niet echt gebruikt, maar je vertelt hoe het zich moet gedragen
    }

    @Test
    public void getAllBookings_void_returnEmptyArrayList() {
        List<Booking> expectedResult = new ArrayList<>();

        List<Booking> actualResult = this.controller.getAllBookings();

        assertThat(actualResult, equalTo(expectedResult));
    }
}
