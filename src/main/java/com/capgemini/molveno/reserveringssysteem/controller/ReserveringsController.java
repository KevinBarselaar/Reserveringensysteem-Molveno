package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.Reservering;
import com.capgemini.molveno.reserveringssysteem.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
