package com.capgemini.molveno.reserveringssysteem.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
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

    private int numberOfMinors;

    private int numberOfAdults;
    private String extraAccommodation;

    /**
     * Customer of the hotel
     */
    public Booking() {
    }

    public Booking(List<Room> rooms, String extraAccommodation, LocalDateTime start, LocalDateTime end) {
        this.rooms = rooms;
        this.extraAccommodation = extraAccommodation;
        this.startBooking = start;
        this.endBooking = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
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

    public int getNumberOfMinors() {
        return numberOfMinors;
    }

    public void setNumberOfMinors(int numberOfMinors) {
        this.numberOfMinors = numberOfMinors;
    }

    public String getExtraAccommodation() {
        return extraAccommodation;
    }

    public void setExtraAccommodation(String extraAccommodation) {
        this.extraAccommodation = extraAccommodation;
    }

    @Override
    public boolean equals(Object obj) {
        Booking booking = (Booking) obj;

        return booking.endBooking.equals(this.endBooking)
                && booking.startBooking.equals(this.startBooking)
                && booking.extraAccommodation.equals(this.extraAccommodation)
                && booking.id == this.id
                && Arrays.equals(booking.rooms.toArray(), this.rooms.toArray())
                && booking.guest.equals(this.guest)
                && booking.numberOfAdults == this.numberOfAdults
                && booking.numberOfMinors == this.numberOfMinors;
    }

    @Override
    public String toString() {
        return id + ", " + rooms + ", " + startBooking + ", " + endBooking + ", " + guest + ", " + numberOfMinors + ", " + numberOfAdults + ", " + extraAccommodation;
    }
}
