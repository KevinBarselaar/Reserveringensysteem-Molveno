package com.capgemini.molveno.bookingsystem.services;

import com.capgemini.molveno.bookingsystem.model.RestaurantBooking;
import com.capgemini.molveno.bookingsystem.repository.RestaurantBookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RestaurantBookingServiceTest {

    @Mock
    private RestaurantBookingRepository repository;

    private RestaurantBookingService service;

    @BeforeEach
    void setupTest() {
        MockitoAnnotations.initMocks(this);

        this.service = new RestaurantBookingService(repository);

    }

    @Test
    void create_restaurantBookingWithTime600_throwsException() {
        RestaurantBooking inputRestaurantBooking = new RestaurantBooking();
        inputRestaurantBooking.setBookingTime("06:00");
        when(repository.saveAndFlush(inputRestaurantBooking)).thenReturn(inputRestaurantBooking);

        assertThrows(Exception.class, () -> {
            this.service.create(inputRestaurantBooking);
        });

        verify(repository, never()).saveAndFlush(inputRestaurantBooking);
    }

    @Test
    void create_restaurantBookingWithTime1200_createsRestaurantBooking() {
        RestaurantBooking inputRestaurantBooking = new RestaurantBooking();
        inputRestaurantBooking.setBookingTime("12:00");
        when(repository.saveAndFlush(inputRestaurantBooking)).thenReturn(inputRestaurantBooking);

        this.service.create(inputRestaurantBooking);

        verify(repository).saveAndFlush(inputRestaurantBooking);
    }
}
