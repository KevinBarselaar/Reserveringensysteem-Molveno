package com.capgemini.molveno.reserveringssysteem.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Room> rooms;

    @DateTimeFormat
    private String startBooking;
    private String endBooking;

    public Booking() {
    }
    
    public Booking(List<Room> rooms, LocalDateTime start, LocalDateTime end) {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        this.rooms = rooms;
        this.startBooking = start.format(outputFormat);
        this.endBooking = end.format(outputFormat);
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

    public String getStartBooking() {
        return startBooking;
    }

    public String getEndBooking() {
        return endBooking;
    }
}
