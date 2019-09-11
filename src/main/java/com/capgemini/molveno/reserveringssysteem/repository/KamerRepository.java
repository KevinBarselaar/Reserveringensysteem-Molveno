package com.capgemini.molveno.reserveringssysteem.repository;

import com.capgemini.molveno.reserveringssysteem.model.BedType;
import com.capgemini.molveno.reserveringssysteem.model.Kamer;
import com.capgemini.molveno.reserveringssysteem.model.KamerType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class KamerRepository { //TODO make this a Jpa repository

    private List<Kamer> nepKamers;

    public KamerRepository() {
        this.nepKamers = new ArrayList<>(); //TODO delete, this is test data

        nepKamers.add(new Kamer(1, KamerType.SINGLE, 1, 0, false, 1, BedType.SINGLE));
        nepKamers.add(new Kamer(2, KamerType.DOUBLE, 2, 0, false, 2, BedType.DOUBLE));
        nepKamers.add(new Kamer(3, KamerType.TWO_DOUBLE, 4, 2, true, 1, BedType.DOUBLE, BedType.BABY, BedType.BABY));
        nepKamers.add(new Kamer(4, KamerType.PENTHOUSE, 3, 1, false, 1, BedType.DOUBLE, BedType.BABY));
    }

    public List<Kamer> all() {
        return this.nepKamers;
    }

    public Kamer find(int id) {
        return this.nepKamers.get(id);
    }
}
