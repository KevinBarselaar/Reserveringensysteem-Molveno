package com.capgemini.molveno.bookingsystem.controller;

import com.capgemini.molveno.bookingsystem.model.Room;
import com.capgemini.molveno.bookingsystem.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class handling all the endpoint requests from the application regarding rooms
 */
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService service) {
        this.roomService = service;
    }

    /**
     * Shows every {@link Room room} and the info contained in them
     *
     * @return JSON response of all {@link Room rooms} in the database
     */
    @GetMapping("/overview")
    public List<Room> getAllRooms() {
        return this.roomService.findAll();
    }

    @GetMapping("/overview/available")
    public List<Room> getAllAvailableRooms() {
        return this.roomService.findAllAvailable();
    }

//    @GetMapping("/overview/available")
//    public List<Room> getAllAvailableRooms(String startDate, String endDate) {
//        return null;
//    }

    /**
     * Request to show the information of a single {@link Room room}
     *
     * @param id {@link Long Number} of the {@link Room room} in the database
     * @return JSON response containing the {@link Room room}
     */
    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return this.roomService.findById(id);
    }

    @PostMapping("/create")
    public Room create(@RequestBody Room newRoom) {
        return this.roomService.create(newRoom);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        this.roomService.deleteById(id);
    }
}
