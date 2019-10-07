package com.capgemini.molveno.bookingsystem.services;

import com.capgemini.molveno.bookingsystem.model.RestaurantBooking;
import com.capgemini.molveno.bookingsystem.repository.RestaurantBookingRepository;
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

//    public void checkRestaurantCapacity() {
//        RestaurantBooking booking = new RestaurantBooking();
//
//        int maxCapacity = 102;
//        int numberOfGuests = booking.getNumberOfGuests();
//        int numberOfMinors = booking.getNumberOfMinors();
//        int totalNumberOfGuests = numberOfGuests + numberOfMinors;
//
//        if (totalNumberOfGuests > 102) {
//            System.out.println("NOPE");
//        }
//        else if (totalNumberOfGuests < 1) {
//            System.out.println("Whoops..");
//        }
//        else {
//            maxCapacity = maxCapacity - totalNumberOfGuests;
//        }
//
//    }
}
