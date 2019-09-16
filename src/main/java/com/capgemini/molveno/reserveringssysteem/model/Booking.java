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
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        this.rooms = rooms;
        this.startBooking = outputFormat.format(LocalDateTime.parse(start.toString(), inputFormat));
        this.endBooking = outputFormat.format(LocalDateTime.parse(end.toString(), inputFormat));
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
