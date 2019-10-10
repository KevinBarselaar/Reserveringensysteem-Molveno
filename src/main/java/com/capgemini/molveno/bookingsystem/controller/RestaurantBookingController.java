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
     * Get all {@link RestaurantBooking restaurant bookings}
     *
     * @return  JSON response of all {@link RestaurantBooking restaurant bookings}
     */
    @GetMapping("/overview")
    public List<RestaurantBooking> getAllBookings() {
        return this.restaurantBookingService.findAll();
    }

    /**
     * Get a {@link RestaurantBooking restaurant booking} based on its id
     *
     * @param id    {@link Long Id} of the {@link RestaurantBooking restaurant booking} to be found
     * @return      JSON response of a {@link RestaurantBooking restaurant booking} with the given id
     */
    @GetMapping("/{id}")
    public RestaurantBooking getBookingById(@PathVariable Long id) {
        return this.restaurantBookingService.findById(id);
    }

    /**
     * Create a new {@link RestaurantBooking restaurant booking}
     *
     * @param booking   {@link RestaurantBooking Restaurant booking} to be made
     */
    @PostMapping("/create")
    public void createBooking(@RequestBody RestaurantBooking booking) {
        this.restaurantBookingService.create(booking);
    }

    /**
     * Deletes a {@link RestaurantBooking restaurant booking} based on its id
     *
     * @param id    {@link Long Id} of the {@link RestaurantBooking restaurant booking} to be deleted
     */
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        this.restaurantBookingService.deleteById(id);
    }
}
