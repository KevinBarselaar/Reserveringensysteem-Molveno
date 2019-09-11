package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.Kamer;
import com.capgemini.molveno.reserveringssysteem.repository.KamerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/kamers")
public class KamerController {

    private final KamerRepository kamerRepository;

    @Autowired
    public KamerController(KamerRepository kamerRepository) {
        this.kamerRepository = kamerRepository;
    }

    @GetMapping("/overzicht")
    public List<Kamer> getAllKamers() {
        return this.kamerRepository.all();
    }

    @GetMapping("/{id}")
    public Kamer getKamer(@PathVariable int id) {
        return this.kamerRepository.find(id - 1);
    }

    //TODO create post / add
}
