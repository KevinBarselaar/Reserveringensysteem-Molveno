package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.Room;
import com.capgemini.molveno.reserveringssysteem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class handling all the endpoint requests from the application regarding rooms
 */
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Shows every {@link Room room} and the info contained in them
     * @return JSON response of all {@link Room rooms} in the database
     */
    @GetMapping("/overview")
    public List<Room> getAllRooms() {
        return this.roomRepository.findAll();
    }

    /**
     * Request to show the information of a single {@link Room room}
     * @param id {@link Long Number} of the {@link Room room} in the database
     * @return JSON response containing the {@link Room room}
     */
    @GetMapping("/{id}")
    public Room getRoom(@PathVariable Long id) {
        return this.roomRepository.findById(Long.valueOf(id)).get();
    }

//TODO create post / add
}
