package com.capgemini.molveno.reserveringssysteem.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "reservering")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Room> rooms;

    @ManyToOne(cascade = CascadeType.ALL)
    private Guest guest;

    /**
     * Customer of the hotel
     */
    private int numberOfAdults;
    private int numberOfKids;

    private String extraAccommodation;

    public Booking() {

    }

    //TODO: remove id from constructor
    public Booking(Long id, List<Room> rooms, String extraAccommodation) {
        this.id = id;
        this.rooms = rooms;
        this.extraAccommodation = extraAccommodation;
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

    public int getNumberOfKids() {
        return numberOfKids;
    }

    public void setNumberOfKids(int numberOfKids) {
        this.numberOfKids = numberOfKids;
    }

    public String getExtraAccommodation() {
        return extraAccommodation;
    }

    public void setExtraAccommodation(String extraAccommodation) {
        this.extraAccommodation = extraAccommodation;
    }
}
