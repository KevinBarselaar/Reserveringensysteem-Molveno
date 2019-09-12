package com.capgemini.molveno.reserveringssysteem.model;

import java.util.List;

public class Reservering {

    private int id;
    private List<Kamer> kamers;

    public Reservering(int id, List<Kamer> kamers) {
        this.id = id;
        this.kamers = kamers;
    }

    //Getter for id (Reservation id should not be able to manually change)
    public int getId() {
        return id;
    }

    //Getter and setter for list of rooms
    public List<Kamer> getKamers() {
        return kamers;
    }

    public void setKamers(List<Kamer> kamers) {
        this.kamers = kamers;
    }

}
