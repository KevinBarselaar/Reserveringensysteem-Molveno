package com.capgemini.molveno.reserveringssysteem.model;

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


    private LocalDateTime startBooking;
    private LocalDateTime endBooking;

    @ManyToOne(cascade = CascadeType.ALL)
    private Guest guest;

    /**
     * Customer of the hotel
     */
    private int numberOfAdults;
    private int numberOfMinors;

    public Booking() {
    }

    public Booking(List<Room> rooms, LocalDateTime start, LocalDateTime end) {
        this.rooms = rooms;
        this.startBooking = start;
        this.endBooking = end;
    }

    public Long getId() {
        return id;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    
    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartBooking() {
        return startBooking;
    }

    public void setStartBooking(LocalDateTime startBooking) {
        this.startBooking = startBooking;
    }

    public LocalDateTime getEndBooking() {
        return endBooking;
    }

    public void setEndBooking(LocalDateTime endBooking) {
        this.endBooking = endBooking;

    public int getNumberOfMinors() {
        return numberOfMinors;
    }

    public void setNumberOfMinors(int numberOfMinors) {
        this.numberOfMinors = numberOfMinors;
    }
}
