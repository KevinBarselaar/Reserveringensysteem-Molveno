package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.Kamer;
import com.capgemini.molveno.reserveringssysteem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/kamers")
public class KamerController {

    private final RoomRepository roomRepository;

    @Autowired
    public KamerController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/overzicht")
    public List<Kamer> getAllKamers() {
        return this.roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Kamer getKamer(@PathVariable int id) {
        return this.roomRepository.findById(id - 1).get();
    }

//TODO create post / add
}
