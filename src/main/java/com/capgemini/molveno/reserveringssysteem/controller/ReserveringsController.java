package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.Reservering;
import com.capgemini.molveno.reserveringssysteem.repository.ReserveringsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reserveringen")
public class ReserveringsController {

    private final ReserveringsRepository reservationRepository;

    @Autowired
    public ReserveringsController(ReserveringsRepository reserveringsRepository) {
        this.reservationRepository = reserveringsRepository;
    }

    @GetMapping("/overzicht")
    public List<Reservering> getAllReserverations() {
        return this.reservationRepository.getReserveringen();
    }

    @GetMapping("/{id}")
    public Reservering getReservering(@PathVariable int id) {
        return this.reservationRepository.findReservering(id - 1);
    }
}
