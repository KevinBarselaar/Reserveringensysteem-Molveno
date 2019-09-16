package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.Room;
import com.capgemini.molveno.reserveringssysteem.model.Booking;
import com.capgemini.molveno.reserveringssysteem.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingsController {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingsController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/overview")
    public List<Booking> getAllBookings() {
        return this.bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return this.bookingRepository.findById(id).get();
    }

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

    @PostMapping
    public void createBooking(@RequestBody Room[] rooms) {
        Booking booking = new Booking();
        booking.setRooms(Arrays.asList(rooms));

        this.bookingRepository.saveAndFlush(booking);
    }
}
