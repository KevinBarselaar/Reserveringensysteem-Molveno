package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.Room;
import com.capgemini.molveno.reserveringssysteem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/overview")
    public List<Room> getAllKamers() {
        return this.roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Room getKamer(@PathVariable Long id) {
        return this.roomRepository.findById(Long.valueOf(id)).get();
    }

//TODO create post / add
}
