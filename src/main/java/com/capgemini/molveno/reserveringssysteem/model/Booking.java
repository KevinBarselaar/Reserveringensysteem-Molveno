package com.capgemini.molveno.reserveringssysteem.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Room> rooms;

    @DateTimeFormat (pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime startBooking;
    private LocalDateTime endBooking;

    public Booking() {

    }

    //TODO: remove id from constructor
    public Booking(List<Room> rooms, LocalDateTime start, LocalDateTime end) {
        this.rooms = rooms;
        this.startBooking = start;
        this.endBooking = end;
    }

    public Long getId() {
        return id;
    }

    //Getter and setter for list of rooms
    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public LocalDateTime getStartBooking() {
        return startBooking;
    }

    public LocalDateTime getEndBooking() {
        return endBooking;
    }
}
