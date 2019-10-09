package com.capgemini.molveno.bookingsystem.controller;

import com.capgemini.molveno.bookingsystem.model.Booking;
import com.capgemini.molveno.bookingsystem.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * Class handling all the endpoint requests from the application regarding bookings
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController (BookingService service) {
        this.bookingService = service;
    }

    /**
     * Shows a list of all {@link Booking bookings} and the info contained
     *
     * @return JSON response of all {@link Booking bookings} in the database
     */

    @CrossOrigin
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
    @CrossOrigin
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return this.bookingService.findById(id);
    }

    @CrossOrigin
    @GetMapping("/checked-out")
    private List<Booking> getAllCheckedOut() {
        return this.bookingService.findAllCheckedOut();
    }

    /**
     * Request to create a new {@link Booking booking} and add it to the database
     *
     * @param {@link Booking booking} object containing all the data from the front-end form
     */
    @CrossOrigin
    @PostMapping("/create")
    public void createBooking(@RequestBody Booking booking) {
        this.bookingService.create(booking);
    }

    @CrossOrigin
    @DeleteMapping("/{bookingId}/rooms/{roomId}")
    public void removeRooms(@PathVariable Long bookingId, @PathVariable Long roomId) {
        this.bookingService.removeRoomFromBooking(bookingId, roomId);
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        this.bookingService.deleteById(id);
    }

    /**
     * Check in or out a booking and set the availabillity of the rooms in the booking to true or false.
     *
     * @param id {@link Long id} of the {@link Booking booking} in the database
     */
    @CrossOrigin
    @PutMapping("/check-in-out/{id}")
    public void checkInOutBooking(@PathVariable Long id) {
        this.bookingService.checkInOutBooking(id);
    }
}
