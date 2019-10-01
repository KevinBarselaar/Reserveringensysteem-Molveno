package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.RestaurantReservation;
import com.capgemini.molveno.reserveringssysteem.services.RestaurantReservationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

//public class RestaurantReservationControllerTests {
//
//    @Mock
//    private RestaurantReservationService reservationService;
//
//    private RestaurantReservationController reservationController;
//
//    private RestaurantReservation mockedReservation;
//    private List<RestaurantReservation> mockedReservationList;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//
//        this.reservationController = new RestaurantReservationController(reservationService);
//
//        this.mockedReservation = this.createMockedReservation()
//
//    }
//
//}
