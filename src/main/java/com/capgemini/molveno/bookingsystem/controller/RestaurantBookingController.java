package com.capgemini.molveno.bookingsystem.controller;

import com.capgemini.molveno.bookingsystem.model.RestaurantBooking;
import com.capgemini.molveno.bookingsystem.services.RestaurantBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class handling all the endpoint requests from the application regarding restaurant bookings
 */
@RestController
@RequestMapping("/api/v1/restaurantbookings")
public class RestaurantBookingController {

    private final RestaurantBookingService restaurantBookingService;

    @Autowired
    public RestaurantBookingController(RestaurantBookingService restaurantBookingService) {
        this.restaurantBookingService = restaurantBookingService;
    }

    /**
     *
     * @return
     */
    @GetMapping("/overview")
    public List<RestaurantBooking> getAllBookings() {
        return this.restaurantBookingService.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RestaurantBooking getBookingById(@PathVariable Long id) {
        return this.restaurantBookingService.findById(id);
    }

    /**
     *
     * @param booking
     */
    @PostMapping("/create")
    public void createBooking(@RequestBody RestaurantBooking booking) {
        this.restaurantBookingService.create(booking);
    }

    /**
     *
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        this.restaurantBookingService.deleteById(id);
    }
}
