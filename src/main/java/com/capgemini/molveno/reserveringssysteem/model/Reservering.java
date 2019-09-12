package com.capgemini.molveno.reserveringssysteem.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "reservering")
public class Reservering {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Kamer> kamers;

    public Reservering() {

    }

    public Reservering(Long id, List<Kamer> kamers) {
        this.id = id;
        this.kamers = kamers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Getter and setter for list of rooms
    public List<Kamer> getKamers() {
        return kamers;
    }

    public void setKamers(List<Kamer> kamers) {
        this.kamers = kamers;
    }

}
