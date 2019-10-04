package com.capgemini.molveno.bookingsystem.services;

import com.capgemini.molveno.bookingsystem.model.Room;
import com.capgemini.molveno.bookingsystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository repository;

    @Autowired
    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public List<Room> findAll() {
        return this.repository.findAll();
    }

    public Room findById(Long id) {
        return this.repository.findById(id).get(); //TODO throw not found exception if not found
    }

    public List<Room> findAllAvailable() {
        return this.repository.findAllAvailable();
    }

    public Room create(Room room) {
        return this.repository.saveAndFlush(room);
    }

    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
