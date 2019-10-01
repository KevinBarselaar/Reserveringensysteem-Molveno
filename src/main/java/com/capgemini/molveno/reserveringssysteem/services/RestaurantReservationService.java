package com.capgemini.molveno.reserveringssysteem.services;

import com.capgemini.molveno.reserveringssysteem.model.RestaurantReservation;
import com.capgemini.molveno.reserveringssysteem.repository.RestaurantReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantReservationService {

    private final RestaurantReservationRepository reservationRepository;

    @Autowired
    public RestaurantReservationService(RestaurantReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public RestaurantReservation findById(Long id) {
        return this.reservationRepository.findById(id).get();
    }

    public List<RestaurantReservation> findAll() {
        return this.reservationRepository.findAll();
    }

    public void deleteById(Long id) {
        this.reservationRepository.deleteById(id);
    }

    public RestaurantReservation create(RestaurantReservation reservation) {
        return this.reservationRepository.saveAndFlush(reservation);
    }
}
