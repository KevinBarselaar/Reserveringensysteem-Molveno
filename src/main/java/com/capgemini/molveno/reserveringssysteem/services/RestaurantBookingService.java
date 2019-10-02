package com.capgemini.molveno.reserveringssysteem.services;

import com.capgemini.molveno.reserveringssysteem.model.RestaurantBooking;
import com.capgemini.molveno.reserveringssysteem.repository.RestaurantBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantBookingService {

    private final RestaurantBookingRepository reservationRepository;

    @Autowired
    public RestaurantBookingService(RestaurantBookingRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public RestaurantBooking findById(Long id) {
        return this.reservationRepository.findById(id).get();
    }

    public List<RestaurantBooking> findAll() {
        return this.reservationRepository.findAll();
    }

    public void deleteById(Long id) {
        this.reservationRepository.deleteById(id);
    }

    public RestaurantBooking create(RestaurantBooking reservation) {
        return this.reservationRepository.saveAndFlush(reservation);
    }
}
