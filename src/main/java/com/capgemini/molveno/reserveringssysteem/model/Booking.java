package com.capgemini.molveno.reserveringssysteem.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains a single booking, with all the info required to link bookings to a guest.
 */
@Entity(name = "booking")
@EqualsAndHashCode
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Room> rooms;


    private LocalDateTime startBooking;
    private LocalDateTime endBooking;

    /**
     * Customer of the hotel
     */
    @ManyToOne(cascade = CascadeType.ALL)
    private Guest guest;

    private int numberOfMinors;

    private int numberOfAdults;
    private String extraAccommodation;

    /**
     *
     */
    public Booking() {
    }

    /**
     * Constructor for the Booking class
     * @param rooms List of {@link Room rooms} linked to the booking
     * @param extraAccommodation {@link String String} containing any additional comments
     * @param start {@link LocalDateTime Start date} of the booking
     * @param end {@link LocalDateTime End date} of the booking
     */
    public Booking(List<Room> rooms, String extraAccommodation, LocalDateTime start, LocalDateTime end) {
        this.rooms = rooms;
        this.extraAccommodation = extraAccommodation;
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
    public String toString() {
        return id + ", " + rooms + ", " + startBooking + ", " + endBooking + ", " + guest + ", " + numberOfMinors + ", " + numberOfAdults + ", " + extraAccommodation;
    }
}
