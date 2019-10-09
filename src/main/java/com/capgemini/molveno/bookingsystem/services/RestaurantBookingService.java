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
        int minAllowedTime = 800;
        int maxAllowedTime = 2200;

        String number = reservation.getBookingTime();

        //Gets values of chars from time string, skipping the ':'
        char c1 = number.charAt(0);
        char c2 = number.charAt(1);
        char c3 = number.charAt(3);
        char c4 = number.charAt(4);

        //extracts numeric value from the chars
        int a = Character.getNumericValue(c1);
        System.out.println(a);
        int b = Character.getNumericValue(c2);
        System.out.println(b);
        int c = Character.getNumericValue(c3);
        System.out.println(c);
        int d = Character.getNumericValue(c4);
        System.out.println(d);

        String str;

        //adds all ints from step before to a string
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        sb.append(b);
        sb.append(c);
        sb.append(d);

        str = sb.toString();

        //converts string to one integer
        int timeBooking = Integer.parseInt(str);
        System.out.println(timeBooking);


        if (timeBooking < minAllowedTime || timeBooking > maxAllowedTime) {
            throw new RuntimeException("Times are not within allowed bounds");
        } else {
            return this.reservationRepository.saveAndFlush(reservation);
        }
    }
}
