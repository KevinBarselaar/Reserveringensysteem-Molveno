package com.capgemini.molveno.reserveringssysteem.repository;

import com.capgemini.molveno.reserveringssysteem.model.Kamer;
import com.capgemini.molveno.reserveringssysteem.model.Reservering;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReserveringsRepository {

    private List<Reservering> reserveringen;

    public ReserveringsRepository() {
        this.reserveringen = new ArrayList<>();
    }

    public List<Reservering> getReserveringen() {
        return reserveringen;
    }

}
