package com.capgemini.molveno.bookingsystem.controller;

import com.capgemini.molveno.bookingsystem.exception.BookingNotFoundException;
import com.capgemini.molveno.bookingsystem.model.Booking;
import com.capgemini.molveno.bookingsystem.model.Room;
import com.capgemini.molveno.bookingsystem.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/overview")
    public List<Booking> getAllBookings() {
        return this.bookingService.findAll();
    }

    /**
     * Gets a specific {@link Booking booking} and its information
     *
     * @param id    {@link Long Number} of the {@link Booking booking} in the database
     * @return      JSON response containing the {@link Booking booking}
     */
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return this.bookingService.findById(id);
    }

    /**
     * Shows a list of checked out {@link Booking bookings} and the info contained
     *
     * @return  JSON response of checked out {@link Booking bookings} in the database
     */
    @GetMapping("/checked-out")
    private List<Booking> getAllCheckedOut() {
        return this.bookingService.findAllCheckedOut();
    }

    /**
     * Request to create a new {@link Booking booking} and add it to the database
     *
     * @param booking   {@link Booking Booking} object containing all the data from the front-end form
     */
    @PostMapping("/create")
    public void createBooking(@RequestBody Booking booking) {
        this.bookingService.create(booking);
    }

    /**
     * Updates an existing booking
     *
     * @param booking                   {@link Booking Booking} to be updated
     * @throws BookingNotFoundException If the {@link Booking booking} cannot be found
     */
    @PutMapping("/update")
    public void updateBooking(@RequestBody Booking booking) throws BookingNotFoundException {
        this.bookingService.update(booking);
    }

    /**
     * Removes a {@link Room room} from a {@link Booking booking}
     *
     * @param bookingId {@link Long Id} of the {@link Booking booking} to remove a {@link Room room}
     * @param roomId    {@link Long Id} of the {@link Room room} to be removed
     */
    @DeleteMapping("/{bookingId}/rooms/{roomId}")
    public void removeRooms(@PathVariable Long bookingId, @PathVariable Long roomId) {
        this.bookingService.removeRoomFromBooking(bookingId, roomId);
    }

    /**
     * Removes a {@link Booking booking} based on the id
     *
     * @param id    {@link Long Id} of the booking to be deleted
     */
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        this.bookingService.deleteById(id);
    }

    /**
     * Check in or out a booking and set the availability of the rooms in the booking to true or false.
     *
     * @param id    {@link Long id} of the {@link Booking booking} in the database
     */
    @PutMapping("/check-in-out/{id}")
    public void checkInOutBooking(@PathVariable Long id) {
        this.bookingService.checkInOutBooking(id);
    }
}
