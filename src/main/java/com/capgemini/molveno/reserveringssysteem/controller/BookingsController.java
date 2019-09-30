package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.Booking;
import com.capgemini.molveno.reserveringssysteem.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class handling all the endpoint requests from the application regarding bookings
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingsController {

    private final BookingService bookingService;

    @Autowired
    public BookingsController(BookingService service) {
        this.bookingService = service;
    }

    /**
     * Shows a list of all {@link Booking bookings} and the info contained
     *
     * @return JSON response of all {@link Booking bookings} in the database
     */
    @GetMapping("/overview")
    public List<Booking> getAllBookings() {
        return this.bookingService.findAll();
    }

    /**
     * Gets a specific {@link Booking booking} and its information
     *
     * @param id {@link Long Number} of the {@link Booking booking} in the database
     * @return JSON response containing the {@link Booking booking}
     */
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return this.bookingService.findById(id);
    }

    @GetMapping("/checkedout")
    private List<Booking> getAllCheckedOut() {
        return this.bookingService.findAllCheckedOut();
    }

    /**
     * Request to create a new {@link Booking booking} and add it to the database
     *
     * @param {@link Booking booking} object containing all the data from the front-end form
     */
    @PostMapping("/create")
    public void createBooking(@RequestBody Booking booking) {
        this.bookingService.create(booking);
    }

    @DeleteMapping("/{bookingId}/rooms/{roomId}")
    public void removeRooms(@PathVariable Long bookingId, @PathVariable Long roomId) {
        this.bookingService.removeRoomFromBooking(bookingId, roomId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        this.bookingService.deleteById(id);
    }
}
