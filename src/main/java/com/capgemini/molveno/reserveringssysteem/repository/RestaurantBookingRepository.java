package com.capgemini.molveno.reserveringssysteem.repository;

import com.capgemini.molveno.reserveringssysteem.model.RestaurantBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantBookingRepository extends JpaRepository<RestaurantBooking, Long> {
}
