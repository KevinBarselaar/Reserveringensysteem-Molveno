package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.RestaurantBooking;
import com.capgemini.molveno.reserveringssysteem.services.RestaurantBookingService;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

       // this.mockedBooking = this.createMockedBooking();

    }

}
