package com.capgemini.molveno.reserveringssysteem.controller;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;
import com.capgemini.molveno.reserveringssysteem.exception.DeadlineExpiredException;
import com.capgemini.molveno.reserveringssysteem.model.Room;
import com.capgemini.molveno.reserveringssysteem.model.Booking;
import com.capgemini.molveno.reserveringssysteem.repository.BookingRepository;
import com.capgemini.molveno.reserveringssysteem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Class handling all the endpoint requests from the application regarding bookings
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingsController {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingsController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * Shows a list of all {@link Booking bookings} and the info contained
     * @return JSON response of all {@link Booking bookings} in the database
     */
    @GetMapping("/overview")
    public List<Booking> getAllBookings() {
        return this.bookingRepository.findAll();
    }

    /**
     * Gets a specific {@link Booking booking} and its information
     * @param id {@link Long Number} of the {@link Booking booking} in the database
     * @return JSON response containing the {@link Booking booking}
     */
    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return this.bookingRepository.findById(id).get();
    }

    /**
     * Shows a list of {@link Booking bookings} in readable language
     * @return {@link String String} of all the {@link Booking bookings} and which rooms are in each
     */
    @GetMapping("/display")
    public String displayBooking() {
        List<Booking> bookings = this.bookingRepository.findAll();
        String display = "";

        for (Booking booking : bookings) {
            display += "Er is een reservering met de kamers: ";
            for (Room kamers : booking.getRooms()) {
                display += "Kamer: " + kamers.getId() + ". ";
            }
            display += "\n";
        }
        return display;
    }

    /**
     * Request to create a new {@link Booking booking} and add it to the database
     * @param rooms List of {@link Room rooms} to be added to the new booking
     */
    @PostMapping
    public void createBooking(@RequestBody Room[] rooms) {
        Booking booking = new Booking();
        booking.setRooms(Arrays.asList(rooms));

        LocalDateTime creationDate = LocalDateTime.now();

        this.bookingRepository.saveAndFlush(booking);
    }

    @DeleteMapping("{bookingId}/rooms/{roomId}")
    public void removeRooms(@PathVariable Long bookingId, @PathVariable Long roomId) {
        Booking booking = this.getBooking(bookingId);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadline = booking.getCreationDate().plusHours(1);

        if (now.isBefore(deadline)) {
            int roomIndexToRemove = -1;

            for(int index = 0; index < booking.getRooms().size(); index++) {
                if(booking.getRooms().get(index).getId().equals(roomId)) {
                    roomIndexToRemove = index;
                    break;
                }
            }

            if(roomIndexToRemove >= 0) {
                booking.getRooms().remove(roomIndexToRemove);

                if(booking.getRooms().isEmpty()) {
                    this.bookingRepository.delete(booking);
                } else {
                    this.bookingRepository.save(booking);
                }
            }
        }
        else {
            throw new DeadlineExpiredException(booking);
        }

    }
}
