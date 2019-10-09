package com.capgemini.molveno.bookingsystem.repository;

import com.capgemini.molveno.bookingsystem.model.RestaurantBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantBookingRepository extends JpaRepository<RestaurantBooking, Long> {
}
