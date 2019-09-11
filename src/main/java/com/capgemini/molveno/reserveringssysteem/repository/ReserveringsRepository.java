package com.capgemini.molveno.reserveringssysteem.repository;

import com.capgemini.molveno.reserveringssysteem.model.Kamer;
import com.capgemini.molveno.reserveringssysteem.model.Reservering;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ReserveringsRepository {

    private List<Reservering> reserveringen;

    private List<Kamer> kamers;
    private KamerRepository kamerRepository;

    public ReserveringsRepository() {
        this.reserveringen = new ArrayList<>();
        this.kamers = new ArrayList<>();
        this.kamerRepository = new KamerRepository();

        //Fill with all kamers
        kamers = kamerRepository.all();

        Reservering reservering2 = new Reservering(2, new ArrayList<>(Arrays.asList(kamers.get(3))));

        //Add to reserveringen
        reserveringen.add(new Reservering(1, new ArrayList<>(Arrays.asList(kamers.get(0), kamers.get(2)))));
        reserveringen.add(reservering2);
    }

    public List<Reservering> getReserveringen() {
        return reserveringen;
    }

    public Reservering findReservering(int id) {
        return reserveringen.get(id);
    }

}
