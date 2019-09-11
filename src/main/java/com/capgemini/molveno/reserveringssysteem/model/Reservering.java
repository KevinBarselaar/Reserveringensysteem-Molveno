package com.capgemini.molveno.reserveringssysteem.model;

import java.util.Date;
import java.util.List;

public class Reservering {

    private int id;
    private List<Kamer> kamers;

    public Reservering(int id, List<Kamer> kamers) {
        this.id = id;
        this.kamers = kamers;
    }

    //Getters for class variables
    public int getId() {
        return id;
    }

    public List<Kamer> getKamers() {
        return kamers;
    }

    //Setters for class variables
    public void setId(int id) {
        this.id = id;
    }

    public void setKamers(List<Kamer> kamers) {
        this.kamers = kamers;
    }
}
