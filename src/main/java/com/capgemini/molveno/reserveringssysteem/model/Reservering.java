package com.capgemini.molveno.reserveringssysteem.model;

import java.util.Date;
import java.util.List;

public class Reservering {

    private int id;
    private Date beginDatum;
    private Date eindDatum;
    private List<Kamer> kamers;

    public Reservering(int id, Date beginDatum, Date eindDatum, List<Kamer> kamers) {
        this.id = id;
        this.beginDatum = beginDatum;
        this.eindDatum = eindDatum;
        this.kamers = kamers;
    }
}
