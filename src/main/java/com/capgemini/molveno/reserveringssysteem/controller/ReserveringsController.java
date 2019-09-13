package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.Kamer;
import com.capgemini.molveno.reserveringssysteem.model.Reservering;
import com.capgemini.molveno.reserveringssysteem.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/reserveringen")
public class ReserveringsController {

    private final BookingRepository bookingRepository;

    @Autowired
    public ReserveringsController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/overzicht")
    public List<Reservering> getAllReserverations() {
        return this.bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reservering getReservering(@PathVariable Long id) {
        return this.bookingRepository.findById(id).get();
    }

    /**
     * Er is een reserveringen, met geboekte kamers: kamer 1, kamer 2
     */

    @GetMapping("/display")
    public String displayBooking() {
        List<Reservering> bookings = this.bookingRepository.findAll();
        String display = "";

        for (Reservering reservering : bookings) {
            display += "Er is een reservering met de kamers: ";
            for (Kamer kamers : reservering.getKamers()) {
                display += "Kamer: " + kamers.getId() + ". ";
            }
        }
        return display;
    }

    @PostMapping
    public void createBooking(@RequestBody Kamer[] kamers) {
        Reservering reservering = new Reservering();
        reservering.setKamers(Arrays.asList(kamers));

        this.bookingRepository.saveAndFlush(reservering);
    }
}
