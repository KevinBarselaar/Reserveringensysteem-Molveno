package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.ExtraItems;
import com.capgemini.molveno.reserveringssysteem.model.RestaurantBooking;
import com.capgemini.molveno.reserveringssysteem.services.RestaurantBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurantbookings")
public class RestaurantBookingController {

    private final RestaurantBookingService restaurantBookingService;

    @Autowired
    public RestaurantBookingController(RestaurantBookingService restaurantBookingService) {
        this.restaurantBookingService = restaurantBookingService;
    }

    @GetMapping("/overview")
    public List<RestaurantBooking> getAllReservations() {
        return this.restaurantBookingService.findAll();
    }

    @GetMapping("/{id}")
    public RestaurantBooking getReservationById(@PathVariable Long id) {
        return this.restaurantBookingService.findById(id);
    }

    @PostMapping("/create")
    public void createReservation(@RequestBody RestaurantBooking reservation) {
        this.restaurantBookingService.create(reservation);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        this.restaurantBookingService.deleteById(id);
    }

    @GetMapping("/nepreservering")
    public RestaurantBooking getFakeReservation() {

        ExtraItems fakeExtraItems = new ExtraItems(true, false, 2, 23);

        RestaurantBooking reservation = new RestaurantBooking("Henk", LocalDateTime.now(), 6, Arrays.asList(fakeExtraItems) , 200, 7);

        return reservation;
    }

}
