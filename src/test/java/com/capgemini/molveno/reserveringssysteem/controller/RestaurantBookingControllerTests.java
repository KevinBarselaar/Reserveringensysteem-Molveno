package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.Booking;
import com.capgemini.molveno.reserveringssysteem.model.ExtraItems;
import com.capgemini.molveno.reserveringssysteem.model.RestaurantBooking;
import com.capgemini.molveno.reserveringssysteem.services.RestaurantBookingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class RestaurantBookingControllerTests {

    @Mock
    private RestaurantBookingService bookingService;

    private RestaurantBookingController bookingController;

    private RestaurantBooking mockedBooking;
    private List<RestaurantBooking> mockedBookingList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.bookingController = new RestaurantBookingController(bookingService);

       this.mockedBooking = this.createMockedBooking(8L, new ArrayList<>(Arrays.asList(this.createMockedExtraItems(6L))));
       this.mockedBookingList = new ArrayList<>(Arrays.asList(mockedBooking));

        when(bookingService.findAll()).thenReturn(this.mockedBookingList);
        when(bookingService.findById(8L)).thenReturn(mockedBooking);
    }

    @Test
    public void getAllBookings_void_returnEmptyArrayList() {
        List<RestaurantBooking> expectedResult = this.mockedBookingList;

        List<RestaurantBooking> actualResult = this.bookingController.getAllBookings();

        assertThat(actualResult, equalTo(expectedResult));
    }
    







    private ExtraItems createMockedExtraItems(Long id) {
        ExtraItems extraItems = new ExtraItems(true, true, 5, 6);
        extraItems.setId(id);

        return extraItems;
    }


    private RestaurantBooking createMockedBooking(Long bookingId, List<ExtraItems> extraItems) {
        RestaurantBooking booking = new RestaurantBooking("piet", LocalDateTime.now(), 3,
                extraItems, 4, 5);
        booking.setBookingId(bookingId);

        return booking;
    }



}
