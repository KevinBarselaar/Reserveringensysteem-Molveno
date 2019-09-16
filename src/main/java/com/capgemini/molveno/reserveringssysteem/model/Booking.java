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

    public Booking() {

    }

    //TODO: remove id from constructor
    public Booking(Long id, List<Room> rooms) {
        this.id = id;
        this.rooms = rooms;
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

}
